#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int n) {
    const int MOD = 1000000007;

    // dp[0]..dp[6]
    static const int base[7] = {1, 1, 3, 10, 23, 62, 170};

    if (n <= 6) return base[n];

    long long *dp = (long long*)calloc((size_t)n + 1, sizeof(long long));
    long long *s  = (long long*)calloc((size_t)n + 1, sizeof(long long));
    if (!dp || !s) {
        free(dp); free(s);
        return 0; // allocation failed (not expected in judge)
    }

    for (int i = 0; i <= 6; i++) dp[i] = base[i];

    // s[i] = dp[i] + s[i-3]
    s[0] = dp[0];
    s[1] = dp[1];
    s[2] = dp[2];
    for (int i = 3; i <= 6; i++) {
        s[i] = (dp[i] + s[i - 3]) % MOD;
    }

    for (int i = 7; i <= n; i++) {
        long long val = 0;
        val += dp[i - 1];
        val += 2LL * dp[i - 2];
        val += 5LL * dp[i - 3];
        val += 2LL * s[i - 4];
        val += 2LL * s[i - 5];
        val += 4LL * s[i - 6];

        dp[i] = val % MOD;
        s[i]  = (dp[i] + s[i - 3]) % MOD;
    }

    int answer = (int)(dp[n] % MOD);
    free(dp);
    free(s);
    return answer;
}