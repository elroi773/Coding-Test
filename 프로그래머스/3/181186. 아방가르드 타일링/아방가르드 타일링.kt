class Solution {
    fun solution(n: Int): Int {
        val MOD = 1_000_000_007L
        val base = longArrayOf(1, 1, 3, 10, 23, 62, 170) // dp[0]..dp[6]

        if (n <= 6) return base[n].toInt()

        val dp = LongArray(n + 1)
        val s = LongArray(n + 1) // s[i] = dp[i] + s[i-3]

        for (i in 0..6) dp[i] = base[i]

        s[0] = dp[0]
        s[1] = dp[1]
        s[2] = dp[2]
        for (i in 3..6) {
            s[i] = (dp[i] + s[i - 3]) % MOD
        }

        for (i in 7..n) {
            var v = 0L
            v += dp[i - 1]
            v += 2L * dp[i - 2]
            v += 5L * dp[i - 3]
            v += 2L * s[i - 4]
            v += 2L * s[i - 5]
            v += 4L * s[i - 6]

            dp[i] = v % MOD
            s[i] = (dp[i] + s[i - 3]) % MOD
        }

        return (dp[n] % MOD).toInt()
    }
}