def solution(n, money):
    MOD = 1_000_000_007
    dp = [0] * (n + 1)
    dp[0] = 1

    for coin in money:
        for i in range(coin, n + 1):
            dp[i] = (dp[i] + dp[i - coin]) % MOD

    return dp[n]