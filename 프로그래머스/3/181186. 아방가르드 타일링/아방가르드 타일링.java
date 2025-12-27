import java.util.*;

class Solution {
    public int solution(int n) {
        final int MOD = 1_000_000_007;

        int[] base = {1, 1, 3, 10, 23, 62, 170}; // dp[0]..dp[6]
        if (n <= 6) return base[n];

        long[] dp = new long[n + 1];
        long[] s  = new long[n + 1];

        for (int i = 0; i <= 6; i++) dp[i] = base[i];

        // s[i] = dp[i] + s[i-3]
        s[0] = dp[0];
        s[1] = dp[1];
        s[2] = dp[2];
        for (int i = 3; i <= 6; i++) {
            s[i] = (dp[i] + s[i - 3]) % MOD;
        }

        for (int i = 7; i <= n; i++) {
            long val = 0;
            val += dp[i - 1];
            val += 2L * dp[i - 2];
            val += 5L * dp[i - 3];
            val += 2L * s[i - 4];
            val += 2L * s[i - 5];
            val += 4L * s[i - 6];

            dp[i] = val % MOD;
            s[i]  = (dp[i] + s[i - 3]) % MOD;
        }

        return (int)(dp[n] % MOD);
    }
}