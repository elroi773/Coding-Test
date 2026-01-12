def solution(clockHands):
    n = len(clockHands)
    best = float("inf")

    def press(b, r, c, t):
        if t == 0:
            return
        b[r][c] = (b[r][c] + t) & 3
        if r > 0:
            b[r - 1][c] = (b[r - 1][c] + t) & 3
        if r < n - 1:
            b[r + 1][c] = (b[r + 1][c] + t) & 3
        if c > 0:
            b[r][c - 1] = (b[r][c - 1] + t) & 3
        if c < n - 1:
            b[r][c + 1] = (b[r][c + 1] + t) & 3

    # 4^n = 2^(2n): 첫 행 각 칸(0~3회)을 2비트씩 표현
    for mask in range(1 << (2 * n)):
        b = [row[:] for row in clockHands]
        cnt = 0
        tmp = mask

        # 1) 첫 행 조작 적용
        for c in range(n):
            t = tmp & 3
            tmp >>= 2
            cnt += t
            press(b, 0, c, t)

        # 2) 2행~n행: 윗칸을 0으로 만들도록 강제 조작
        for r in range(1, n):
            for c in range(n):
                above = b[r - 1][c]
                if above != 0:
                    t = (4 - above) & 3  # 1~3
                    cnt += t
                    press(b, r, c, t)

        # 3) 마지막 행이 모두 0이면 성공
        if all(b[n - 1][c] == 0 for c in range(n)):
            best = min(best, cnt)

    return best