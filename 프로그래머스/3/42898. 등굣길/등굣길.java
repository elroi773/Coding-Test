import java.util.*;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        final int MOD = 1_000_000_007;

        // puddles: (x, y) = (열, 행) 1-indexed
        boolean[][] blocked = new boolean[n + 1][m + 1];
        for (int[] p : puddles) {
            int x = p[0], y = p[1];
            blocked[y][x] = true;
        }

        int[][] dp = new int[n + 1][m + 1];
        dp[1][1] = 1;

        for (int y = 1; y <= n; y++) {
            for (int x = 1; x <= m; x++) {
                if (blocked[y][x]) {
                    dp[y][x] = 0;
                    continue;
                }
                if (x == 1 && y == 1) continue;

                long ways = 0;
                ways += dp[y - 1][x]; // 위
                ways += dp[y][x - 1]; // 왼쪽
                dp[y][x] = (int)(ways % MOD);
            }
        }

        return dp[n][m];
    }
}