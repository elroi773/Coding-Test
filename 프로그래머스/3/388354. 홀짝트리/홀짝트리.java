import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {

        // 노드 번호 최대값
        final int MAX = 1_000_000;

        // 인접 리스트
        ArrayList<Integer>[] graph = new ArrayList[MAX + 1];
        for (int i = 0; i <= MAX; i++) {
            graph[i] = new ArrayList<>();
        }

        // 차수, 방문 여부, 존재 여부
        int[] degree = new int[MAX + 1];
        boolean[] visited = new boolean[MAX + 1];
        boolean[] exists = new boolean[MAX + 1];

        for (int v : nodes) {
            exists[v] = true;
        }

        // 간선 정보
        for (int[] e : edges) {
            int a = e[0];
            int b = e[1];
            graph[a].add(b);
            graph[b].add(a);
            degree[a]++;
            degree[b]++;
        }

        int oddEvenTree = 0;
        int reverseTree = 0;

        // BFS로 각 트리 탐색
        ArrayDeque<Integer> queue = new ArrayDeque<>();

        for (int start : nodes) {
            if (visited[start]) continue;

            queue.clear();
            queue.add(start);
            visited[start] = true;

            int A = 0;
            int B = 0;

            while (!queue.isEmpty()) {
                int cur = queue.poll();

                if ((degree[cur] & 1) == (cur & 1))
                    A++;
                else
                    B++;

                for (int next : graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }

            if (A == 1) oddEvenTree++;
            if (B == 1) reverseTree++;
        }

        return new int[]{oddEvenTree, reverseTree};
    }
}
