def solution(board):
    # 문자 개수 세기
    o_count = sum(row.count('O') for row in board)
    x_count = sum(row.count('X') for row in board)

    # 1️⃣ 기본 개수 규칙 위반 검사
    # X가 O보다 많거나, O가 X보다 2 이상 많으면 불가능
    if not (o_count == x_count or o_count == x_count + 1):
        return 0

    # 2️⃣ 승리 여부 판별 함수
    def win(ch):
        # 가로
        for i in range(3):
            if all(board[i][j] == ch for j in range(3)):
                return True
        # 세로
        for j in range(3):
            if all(board[i][j] == ch for i in range(3)):
                return True
        # 대각선
        if all(board[i][i] == ch for i in range(3)):
            return True
        if all(board[i][2 - i] == ch for i in range(3)):
            return True
        return False

    o_win = win('O')
    x_win = win('X')

    # 3️⃣ 승리 규칙 위반 검사
    # O가 이겼으면 O는 X보다 1개 많아야 함
    if o_win and o_count != x_count + 1:
        return 0
    # X가 이겼으면 O와 X 개수가 같아야 함
    if x_win and o_count != x_count:
        return 0
    # O, X 둘 다 승리하는 경우 불가능
    if o_win and x_win:
        return 0

    # 4️⃣ 위의 모든 규칙을 통과하면 가능
    return 1
