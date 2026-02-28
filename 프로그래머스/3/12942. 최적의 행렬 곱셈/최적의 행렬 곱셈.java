import java.util.*;

class Solution {
    public int solution(int[][] matrix_sizes) {
        int n = matrix_sizes.length;

        // 차원 배열 p (길이 n+1)
        // i번째 행렬: p[i] x p[i+1]
        long[] p = new long[n + 1];
        p[0] = matrix_sizes[0][0];
        for (int i = 0; i < n; i++) {
            p[i + 1] = matrix_sizes[i][1];
        }

        long[][] dp = new long[n][n];
        long INF = Long.MAX_VALUE / 4;

        // 구간 길이 2부터 n까지
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                long best = INF;

                // 분할점 k: (i..k) * (k+1..j)
                for (int k = i; k < j; k++) {
                    long cost = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                    if (cost < best) best = cost;
                }
                dp[i][j] = best;
            }
        }

        return (int) dp[0][n - 1];
    }
}