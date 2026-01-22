def solution(board, skill):
    n = len(board)
    m = len(board[0])

    # (n+1) x (m+1) 2D diff
    diff = [[0] * (m + 1) for _ in range(n + 1)]

    # 4점 업데이트
    for t, r1, c1, r2, c2, degree in skill:
        delta = -degree if t == 1 else degree

        diff[r1][c1] += delta
        if c2 + 1 <= m:
            diff[r1][c2 + 1] -= delta
        if r2 + 1 <= n:
            diff[r2 + 1][c1] -= delta
        if r2 + 1 <= n and c2 + 1 <= m:
            diff[r2 + 1][c2 + 1] += delta

    # 가로 누적합
    for r in range(n + 1):
        run = 0
        row = diff[r]
        for c in range(m + 1):
            run += row[c]
            row[c] = run

    # 세로 누적합
    for c in range(m + 1):
        run = 0
        for r in range(n + 1):
            run += diff[r][c]
            diff[r][c] = run

    # 최종 내구도 > 0 카운트
    answer = 0
    for r in range(n):
        br = board[r]
        dr = diff[r]
        for c in range(m):
            if br[c] + dr[c] > 0:
                answer += 1

    return answer