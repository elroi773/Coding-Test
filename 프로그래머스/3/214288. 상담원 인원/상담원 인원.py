import heapq

def calc_wait(req_list, m):
    """req_list: [(start, duration), ...] sorted by start"""
    if not req_list:
        return 0

    heap = []  # end times of ongoing consultations
    total_wait = 0

    for a, b in req_list:
        if len(heap) < m:
            # free mentor available
            heapq.heappush(heap, a + b)
        else:
            earliest_end = heapq.heappop(heap)
            start_time = max(a, earliest_end)
            total_wait += start_time - a
            heapq.heappush(heap, start_time + b)

    return total_wait


def solution(k, n, reqs):
    # 1) split requests by type
    by_type = [[] for _ in range(k + 1)]
    for a, b, c in reqs:
        by_type[c].append((a, b))

    # 2) precompute cost[type][mentors]
    # cost[t][m] for m=1..n
    cost = [[0] * (n + 1) for _ in range(k + 1)]
    for t in range(1, k + 1):
        for m in range(1, n + 1):
            cost[t][m] = calc_wait(by_type[t], m)

    # 3) DP: dp[i][j] = min wait using j mentors for first i types
    INF = 10**18
    dp = [[INF] * (n + 1) for _ in range(k + 1)]
    dp[0][0] = 0

    for i in range(1, k + 1):
        # need at least i mentors to give >=1 to each of i types
        for j in range(i, n + 1):
            # allocate m mentors to type i (at least 1)
            # remaining j-m must be >= i-1
            max_m = j - (i - 1)
            for m in range(1, max_m + 1):
                dp[i][j] = min(dp[i][j], dp[i - 1][j - m] + cost[i][m])

    return dp[k][n]