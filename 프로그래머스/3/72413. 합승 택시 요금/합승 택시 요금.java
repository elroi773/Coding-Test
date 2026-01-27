import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        final long INF = (long) 1e18;

        long[][] dist = new long[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }

        // 양방향 간선
        for (int[] f : fares) {
            int c = f[0], d = f[1], cost = f[2];
            if (cost < dist[c][d]) {
                dist[c][d] = cost;
                dist[d][c] = cost;
            }
        }

        // Floyd-Warshall: dist[i][j] = min(dist[i][j], dist[i][k] + dist[k][j])
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                if (dist[i][k] == INF) continue;
                long ik = dist[i][k];
                for (int j = 1; j <= n; j++) {
                    long nd = ik + dist[k][j];
                    if (nd < dist[i][j]) dist[i][j] = nd;
                }
            }
        }

        long ans = INF;
        for (int k = 1; k <= n; k++) {
            long cost = dist[s][k] + dist[k][a] + dist[k][b];
            if (cost < ans) ans = cost;
        }

        return (int) ans;
    }
}