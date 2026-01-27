def solution(n, s, a, b, fares):
    INF = 10**18
    dist = [[INF] * (n + 1) for _ in range(n + 1)]
    for i in range(1, n + 1):
        dist[i][i] = 0

    for c, d, f in fares:
        # 양방향
        if f < dist[c][d]:
            dist[c][d] = f
            dist[d][c] = f

    # Floyd-Warshall
    for k in range(1, n + 1):
        dk = dist[k]
        for i in range(1, n + 1):
            if dist[i][k] == INF:
                continue
            ik = dist[i][k]
            di = dist[i]
            for j in range(1, n + 1):
                nd = ik + dk[j]
                if nd < di[j]:
                    di[j] = nd

    ans = INF
    for k in range(1, n + 1):
        cost = dist[s][k] + dist[k][a] + dist[k][b]
        if cost < ans:
            ans = cost

    return ans