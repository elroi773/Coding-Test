def solution(n, m, x, y, queries):
    # 가능한 시작점의 범위(역방향으로 확장)
    r1 = r2 = x  # row [r1, r2]
    c1 = c2 = y  # col [c1, c2]

    for cmd, dx in reversed(queries):
        if cmd == 0:  # (정방향) 왼쪽: col 감소 -> (역방향) 오른쪽으로 되돌림
            if c1 != 0:
                c1 += dx
            # c1 == 0 이면, 원래 더 왼쪽(음수)에서 오지 못하니 0 유지(클램프 구간 포함)
            c2 = min(m - 1, c2 + dx)

        elif cmd == 1:  # (정방향) 오른쪽: col 증가 -> (역방향) 왼쪽으로 되돌림
            c1 = max(0, c1 - dx)
            if c2 != m - 1:
                c2 -= dx
            # c2 == m-1 이면, 클램프되어 m-1로 몰린 시작점들이 존재 가능 => m-1 유지

        elif cmd == 2:  # (정방향) 위: row 감소 -> (역방향) 아래로 되돌림
            if r1 != 0:
                r1 += dx
            r2 = min(n - 1, r2 + dx)

        else:  # cmd == 3 (정방향) 아래: row 증가 -> (역방향) 위로 되돌림
            r1 = max(0, r1 - dx)
            if r2 != n - 1:
                r2 -= dx

        # 범위가 뒤집히면 가능한 시작점이 없음
        if r1 > r2 or c1 > c2:
            return 0

    return (r2 - r1 + 1) * (c2 - c1 + 1)