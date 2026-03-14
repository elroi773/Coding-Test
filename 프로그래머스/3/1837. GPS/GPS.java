import java.util.*;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edge_list) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        final int INF = 1_000_000_000;
        int[][] dp = new int[k][n + 1];

        for (int i = 0; i < k; i++) {
            Arrays.fill(dp[i], INF);
        }

        dp[0][gps_log[0]] = 0;

        for (int t = 1; t < k; t++) {
            for (int v = 1; v <= n; v++) {
                int minPrev = dp[t - 1][v]; // 같은 거점에 머무르기

                for (int prev : graph[v]) {
                    minPrev = Math.min(minPrev, dp[t - 1][prev]);
                }

                if (minPrev == INF) continue;

                dp[t][v] = minPrev + (gps_log[t] == v ? 0 : 1);
            }
        }

        int answer = dp[k - 1][gps_log[k - 1]];
        return answer == INF ? -1 : answer;
    }
}