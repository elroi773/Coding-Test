def rotate90(a):
    n = len(a)
    r = [[0] * n for _ in range(n)]
    for i in range(n):
        for j in range(n):
            r[j][n - 1 - i] = a[i][j]
    return r

def solution(key, lock):
    m = len(key)
    n = len(lock)

    pad = m - 1
    size = n + pad * 2

    # 확장 보드 생성 후 중앙에 lock 배치
    board = [[0] * size for _ in range(size)]
    for i in range(n):
        for j in range(n):
            board[i + pad][j + pad] = lock[i][j]

    def is_unlocked():
        for i in range(n):
            for j in range(n):
                if board[i + pad][j + pad] != 1:
                    return False
        return True

    cur_key = key
    for _ in range(4):
        for x in range(size - m + 1):
            for y in range(size - m + 1):
                # key 더하기
                for i in range(m):
                    for j in range(m):
                        board[x + i][y + j] += cur_key[i][j]

                if is_unlocked():
                    return True

                # 원복
                for i in range(m):
                    for j in range(m):
                        board[x + i][y + j] -= cur_key[i][j]

        cur_key = rotate90(cur_key)

    return False