import java.util.*;

class Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        // 1) 인접 리스트
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        for (int[] e : roads) {
            int a = e[0], b = e[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        // 2) destination 기준 BFS 거리
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        dist[destination] = 0;

        ArrayDeque<Integer> q = new ArrayDeque<>();
        q.add(destination);

        while (!q.isEmpty()) {
            int cur = q.poll();
            int nextDist = dist[cur] + 1;

            for (int nxt : graph[cur]) {
                if (dist[nxt] == -1) {
                    dist[nxt] = nextDist;
                    q.add(nxt);
                }
            }
        }

        // 3) sources 순서대로 답 만들기
        int[] answer = new int[sources.length];
        for (int i = 0; i < sources.length; i++) {
            answer[i] = dist[sources[i]];
        }
        return answer;
    }
}