import java.util.*;

class Solution {

    private long calcWait(List<int[]> reqList, int m) {
        if (reqList.isEmpty()) return 0L;

        // mentors' end times
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        long totalWait = 0L;

        for (int[] r : reqList) {
            int a = r[0], b = r[1];

            if (pq.size() < m) {
                pq.add(a + b);
            } else {
                int earliestEnd = pq.poll();
                int start = Math.max(a, earliestEnd);
                totalWait += (long)(start - a);
                pq.add(start + b);
            }
        }
        return totalWait;
    }

    public int solution(int k, int n, int[][] reqs) {
        // 1) split requests by type (reqs already sorted by a, so each list stays sorted)
        List<int[]>[] byType = new ArrayList[k + 1];
        for (int t = 1; t <= k; t++) byType[t] = new ArrayList<>();

        for (int[] r : reqs) {
            int a = r[0], b = r[1], c = r[2];
            byType[c].add(new int[]{a, b});
        }

        // 2) precompute cost[type][mentors]
        long[][] cost = new long[k + 1][n + 1];
        for (int t = 1; t <= k; t++) {
            for (int m = 1; m <= n; m++) {
                cost[t][m] = calcWait(byType[t], m);
            }
        }

        // 3) DP: dp[i][j] = min wait using j mentors for first i types
        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[k + 1][n + 1];
        for (int i = 0; i <= k; i++) Arrays.fill(dp[i], INF);
        dp[0][0] = 0;

        for (int i = 1; i <= k; i++) {
            for (int j = i; j <= n; j++) { // need at least 1 mentor per type
                int maxM = j - (i - 1);
                for (int m = 1; m <= maxM; m++) {
                    if (dp[i - 1][j - m] == INF) continue;
                    dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - m] + cost[i][m]);
                }
            }
        }

        return (int) dp[k][n];
    }
}