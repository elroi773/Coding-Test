def solution(triangle):
    n = len(triangle)
    dp = [row[:] for row in triangle]  # 복사해서 dp로 사용

    for i in range(1, n):
        for j in range(i + 1):
            from_left = dp[i-1][j-1] if j > 0 else -1   # 왼쪽 위
            from_right = dp[i-1][j] if j < i else -1    # 오른쪽 위
            dp[i][j] = triangle[i][j] + max(from_left, from_right)

    return max(dp[-1])