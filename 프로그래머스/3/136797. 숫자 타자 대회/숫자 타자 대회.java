import java.util.*;

class Solution {

    // 충분히 큰 값
    private static final long INF = (long) 1e18;

    public int solution(String numbers) {
        int[][] cost = buildCost(); // cost[a][b] : a에서 b 누르는 최소 가중치 (a==b이면 1)

        long[][] dp = new long[10][10];
        for (int i = 0; i < 10; i++) Arrays.fill(dp[i], INF);

        // 시작: 왼손 4, 오른손 6
        dp[4][6] = 0;

        for (int idx = 0; idx < numbers.length(); idx++) {
            int d = numbers.charAt(idx) - '0';
            long[][] ndp = new long[10][10];
            for (int i = 0; i < 10; i++) Arrays.fill(ndp[i], INF);

            for (int l = 0; l < 10; l++) {
                for (int r = 0; r < 10; r++) {
                    if (l == r) continue;           // 같은 칸 불가
                    long cur = dp[l][r];
                    if (cur >= INF) continue;

                    if (d == l) {
                        // d 위에 왼손이 이미 있으므로 반드시 왼손으로
                        ndp[l][r] = Math.min(ndp[l][r], cur + 1);
                    } else if (d == r) {
                        // d 위에 오른손이 이미 있으므로 반드시 오른손으로
                        ndp[l][r] = Math.min(ndp[l][r], cur + 1);
                    } else {
                        // 왼손으로 누르기: (d, r)
                        if (d != r) {
                            ndp[d][r] = Math.min(ndp[d][r], cur + cost[l][d]);
                        }
                        // 오른손으로 누르기: (l, d)
                        if (d != l) {
                            ndp[l][d] = Math.min(ndp[l][d], cur + cost[r][d]);
                        }
                    }
                }
            }
            dp = ndp;
        }

        long ans = INF;
        for (int l = 0; l < 10; l++) {
            for (int r = 0; r < 10; r++) {
                if (l == r) continue;
                ans = Math.min(ans, dp[l][r]);
            }
        }
        return (int) ans;
    }

    // 키패드 그래프에서 최단거리로 cost[][] 구성
    private int[][] buildCost() {
        // 좌표: digit -> (x,y)
        int[] x = new int[10];
        int[] y = new int[10];

        // 0
        x[0] = 1; y[0] = 3;
        // 1~9
        x[1] = 0; y[1] = 0;
        x[2] = 1; y[2] = 0;
        x[3] = 2; y[3] = 0;
        x[4] = 0; y[4] = 1;
        x[5] = 1; y[5] = 1;
        x[6] = 2; y[6] = 1;
        x[7] = 0; y[7] = 2;
        x[8] = 1; y[8] = 2;
        x[9] = 2; y[9] = 2;

        // 그래프 구성 (인접: 상하좌우=2, 대각=3)
        List<int[]>[] g = new ArrayList[10];
        for (int i = 0; i < 10; i++) g[i] = new ArrayList<>();

        for (int a = 0; a < 10; a++) {
            for (int b = a + 1; b < 10; b++) {
                int dx = Math.abs(x[a] - x[b]);
                int dy = Math.abs(y[a] - y[b]);
                if (dx == 0 && dy == 0) continue;
                if (dx <= 1 && dy <= 1) {
                    int w = (dx == 0 || dy == 0) ? 2 : 3;
                    g[a].add(new int[]{b, w});
                    g[b].add(new int[]{a, w});
                }
            }
        }

        int[][] cost = new int[10][10];
        for (int s = 0; s < 10; s++) {
            long[] dist = dijkstra(s, g);
            for (int v = 0; v < 10; v++) {
                if (s == v) cost[s][v] = 1;          // 제자리 재입력
                else cost[s][v] = (int) dist[v];
            }
        }
        return cost;
    }

    private long[] dijkstra(int src, List<int[]>[] g) {
        long[] dist = new long[10];
        Arrays.fill(dist, INF);
        dist[src] = 0;

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.add(new long[]{0, src});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1];
            if (d != dist[u]) continue;

            for (int[] e : g[u]) {
                int v = e[0];
                int w = e[1];
                long nd = d + w;
                if (nd < dist[v]) {
                    dist[v] = nd;
                    pq.add(new long[]{nd, v});
                }
            }
        }
        return dist;
    }
}