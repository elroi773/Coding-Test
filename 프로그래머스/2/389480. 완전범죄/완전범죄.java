import java.util.*;

public class Solution {
    public int solution(int[][] info, int n, int m) {
        final int INF = Integer.MAX_VALUE / 4;
        int[] dp = new int[m];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int[] item : info) {
            int aCost = item[0];
            int bCost = item[1];
            int[] newdp = new int[m];
            Arrays.fill(newdp, INF);

            for (int b = 0; b < m; b++) {
                if (dp[b] == INF) continue;

                // A가 훔치는 경우
                int newA = dp[b] + aCost;
                if (newA < n) {
                    newdp[b] = Math.min(newdp[b], newA);
                }

                // B가 훔치는 경우
                int newB = b + bCost;
                if (newB < m) {
                    newdp[newB] = Math.min(newdp[newB], dp[b]);
                }
            }
            dp = newdp;
        }

        int ans = INF;
        for (int val : dp) ans = Math.min(ans, val);
        return (ans == INF) ? -1 : ans;
    }
}
