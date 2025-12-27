def solution(n):
    MOD = 1_000_000_007

    # dp[x] = 3 x x 타일링 경우의 수 (mod MOD)
    dp_base = [1, 1, 3, 10, 23, 62, 170]  # dp[0]..dp[6]
    if n <= 6:
        return dp_base[n] % MOD

    dp = [0] * (n + 1)
    s  = [0] * (n + 1)  # s[i] = dp[i] + s[i-3]

    for i in range(7):
        dp[i] = dp_base[i]

    # s[0]=dp0, s[1]=dp1, s[2]=dp2, 이후 s[i]=dp[i]+s[i-3]
    s[0], s[1], s[2] = dp[0], dp[1], dp[2]
    for i in range(3, 7):
        s[i] = (dp[i] + s[i - 3]) % MOD

    for i in range(7, n + 1):
        val = dp[i - 1]
        val += 2 * dp[i - 2]
        val += 5 * dp[i - 3]
        val += 2 * s[i - 4]
        val += 2 * s[i - 5]
        val += 4 * s[i - 6]
        dp[i] = val % MOD
        s[i] = (dp[i] + s[i - 3]) % MOD

    return dp[n]