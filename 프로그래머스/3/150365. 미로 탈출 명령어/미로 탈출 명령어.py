def solution(n, m, x, y, r, c, k):
    def dist(a, b, c, d):
        return abs(a - c) + abs(b - d)

    d0 = dist(x, y, r, c)
    if d0 > k or (k - d0) % 2 != 0:
        return "impossible"

    cx, cy = x, y
    dirs = [('d', 1, 0), ('l', 0, -1), ('r', 0, 1), ('u', -1, 0)]
    ans = []

    for step in range(k):
        moved = False
        for ch, dx, dy in dirs:
            nx, ny = cx + dx, cy + dy
            if not (1 <= nx <= n and 1 <= ny <= m):
                continue
            rem = k - (step + 1)
            nd = dist(nx, ny, r, c)
            if nd <= rem and (rem - nd) % 2 == 0:
                ans.append(ch)
                cx, cy = nx, ny
                moved = True
                break
        if not moved:
            return "impossible"

    return "".join(ans)