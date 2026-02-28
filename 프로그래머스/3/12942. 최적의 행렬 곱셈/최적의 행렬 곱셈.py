def solution(matrix_sizes):
    n = len(matrix_sizes)

    # 차원 배열 p (길이 n+1)
    # i번째 행렬: p[i] x p[i+1]
    p = [0] * (n + 1)
    p[0] = matrix_sizes[0][0]
    for i in range(n):
        p[i + 1] = matrix_sizes[i][1]

    INF = 10**18
    dp = [[0] * n for _ in range(n)]  # dp[i][j] = i..j 최소 비용

    # 구간 길이 2부터 n까지
    for length in range(2, n + 1):
        for i in range(0, n - length + 1):
            j = i + length - 1
            best = INF
            for k in range(i, j):
                cost = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1]
                if cost < best:
                    best = cost
            dp[i][j] = best

    return dp[0][n - 1]