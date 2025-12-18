def solution(m, n, board):
    answer = 0

    # 문자열 → 2차원 리스트
    board = [list(row) for row in board]

    while True:
        remove = [[False] * n for _ in range(m)]
        count = 0

        # 1️⃣ 2x2 같은 블록 찾기
        for i in range(m - 1):
            for j in range(n - 1):
                cur = board[i][j]
                if cur == '.':
                    continue
                if (board[i][j + 1] == cur and
                    board[i + 1][j] == cur and
                    board[i + 1][j + 1] == cur):
                    remove[i][j] = True
                    remove[i][j + 1] = True
                    remove[i + 1][j] = True
                    remove[i + 1][j + 1] = True

        # 2️⃣ 블록 제거
        for i in range(m):
            for j in range(n):
                if remove[i][j]:
                    board[i][j] = '.'
                    count += 1

        # 더 이상 제거할 블록이 없으면 종료
        if count == 0:
            break
        answer += count

        # 3️⃣ 중력 처리
        for j in range(n):
            empty = m - 1
            for i in range(m - 1, -1, -1):
                if board[i][j] != '.':
                    board[empty][j] = board[i][j]
                    if empty != i:
                        board[i][j] = '.'
                    empty -= 1

    return answer
