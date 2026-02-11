import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        // 1) 인접 리스트
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] e : edge) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        // 2) 거리 배열 (-1: 미방문)
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);

        // 3) BFS
        ArrayDeque<Integer> q = new ArrayDeque<>();
        dist[1] = 0;
        q.add(1);

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nxt : g[cur]) {
                if (dist[nxt] == -1) {
                    dist[nxt] = dist[cur] + 1;
                    q.add(nxt);
                }
            }
        }

        // 4) 최대 거리 + 개수
        int maxDist = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] > maxDist) maxDist = dist[i];
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            if (dist[i] == maxDist) answer++;
        }

        return answer;
    }
}