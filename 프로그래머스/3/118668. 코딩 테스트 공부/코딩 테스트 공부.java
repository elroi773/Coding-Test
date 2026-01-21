import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        final int INF = 1_000_000_000;
        int[][] dp = new int[maxAlp + 1][maxCop + 1];
        for (int i = 0; i <= maxAlp; i++) Arrays.fill(dp[i], INF);
        dp[alp][cop] = 0;

        for (int a = alp; a <= maxAlp; a++) {
            for (int c = cop; c <= maxCop; c++) {
                int cur = dp[a][c];
                if (cur == INF) continue;

                if (a + 1 <= maxAlp) dp[a + 1][c] = Math.min(dp[a + 1][c], cur + 1);
                if (c + 1 <= maxCop) dp[a][c + 1] = Math.min(dp[a][c + 1], cur + 1);

                for (int[] p : problems) {
                    int reqA = p[0], reqC = p[1], rwdA = p[2], rwdC = p[3], cost = p[4];
                    if (a >= reqA && c >= reqC) {
                        int na = Math.min(maxAlp, a + rwdA);
                        int nc = Math.min(maxCop, c + rwdC);
                        dp[na][nc] = Math.min(dp[na][nc], cur + cost);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}