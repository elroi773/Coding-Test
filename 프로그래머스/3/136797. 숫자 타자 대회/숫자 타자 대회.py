def solution(numbers: str) -> int:
    INF = 10**15

    # digit -> (x,y)
    x = [0] * 10
    y = [0] * 10
    x[0], y[0] = 1, 3
    x[1], y[1] = 0, 0
    x[2], y[2] = 1, 0
    x[3], y[3] = 2, 0
    x[4], y[4] = 0, 1
    x[5], y[5] = 1, 1
    x[6], y[6] = 2, 1
    x[7], y[7] = 0, 2
    x[8], y[8] = 1, 2
    x[9], y[9] = 2, 2

    # dist: move-only shortest cost (same digit = 0 here)
    dist = [[INF] * 10 for _ in range(10)]
    for i in range(10):
        dist[i][i] = 0

    # adjacency: orthogonal=2, diagonal=3
    for a in range(10):
        for b in range(a + 1, 10):
            dx = abs(x[a] - x[b])
            dy = abs(y[a] - y[b])
            if dx <= 1 and dy <= 1 and not (dx == 0 and dy == 0):
                w = 2 if (dx == 0 or dy == 0) else 3
                dist[a][b] = w
                dist[b][a] = w

    # Floyd-Warshall (10 nodes)
    for k in range(10):
        dk = dist[k]
        for i in range(10):
            dik = dist[i][k]
            if dik >= INF:
                continue
            di = dist[i]
            for j in range(10):
                nd = dik + dk[j]
                if nd < di[j]:
                    di[j] = nd

    # cost[a][b] = press cost (a==b => 1)
    cost = [[0] * 10 for _ in range(10)]
    for a in range(10):
        for b in range(10):
            cost[a][b] = 1 if a == b else dist[a][b]

    # DP over (left,right) positions, left != right
    dp = [[INF] * 10 for _ in range(10)]
    dp[4][6] = 0  # start

    for ch in numbers:
        d = ord(ch) - 48
        ndp = [[INF] * 10 for _ in range(10)]
        for l in range(10):
            for r in range(10):
                if l == r:
                    continue
                cur = dp[l][r]
                if cur >= INF:
                    continue

                if d == l:
                    # must press with left
                    v = cur + 1
                    if v < ndp[l][r]:
                        ndp[l][r] = v
                elif d == r:
                    # must press with right
                    v = cur + 1
                    if v < ndp[l][r]:
                        ndp[l][r] = v
                else:
                    # press with left -> (d, r)
                    v1 = cur + cost[l][d]
                    if v1 < ndp[d][r]:
                        ndp[d][r] = v1
                    # press with right -> (l, d)
                    v2 = cur + cost[r][d]
                    if v2 < ndp[l][d]:
                        ndp[l][d] = v2

        dp = ndp

    return min(dp[l][r] for l in range(10) for r in range(10) if l != r)