def solution(info, n, m):
    INF = 10**9
    dp = [INF] * m
    dp[0] = 0

    for a_cost, b_cost in info:
        newdp = [INF] * m
        for b in range(m):
            if dp[b] == INF:
                continue

            # A가 훔치는 경우
            newA = dp[b] + a_cost
            if newA < n:
                newdp[b] = min(newdp[b], newA)

            # B가 훔치는 경우
            newB = b + b_cost
            if newB < m:
                newdp[newB] = min(newdp[newB], dp[b])

        dp = newdp

    ans = min(dp)
    return -1 if ans == INF else ans

