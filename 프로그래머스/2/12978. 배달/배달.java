import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        // 1. 그래프 생성
        List<int[]>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] r : road) {
            int a = r[0];
            int b = r[1];
            int c = r[2];
            graph[a].add(new int[]{b, c});
            graph[b].add(new int[]{a, c});
        }
        
        // 2. 다익스트라 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>(
            (o1, o2) -> o1[0] - o2[0]
        );
        pq.offer(new int[]{0, 1}); // {거리, 마을}
        
        // 3. 다익스트라 실행
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int curDist = cur[0];
            int curNode = cur[1];
            
            if (curDist > dist[curNode]) continue;
            
            for (int[] next : graph[curNode]) {
                int nextNode = next[0];
                int cost = next[1];
                
                int newDist = curDist + cost;
                if (newDist < dist[nextNode]) {
                    dist[nextNode] = newDist;
                    pq.offer(new int[]{newDist, nextNode});
                }
            }
        }
        
        // 4. K 이하 마을 개수 세기
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }
        
        return answer;
    }
}
