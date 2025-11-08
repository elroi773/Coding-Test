import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        // 그래프(인접 리스트) 생성
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int minDiff = n; // 가능한 최대 차이로 초기화

        // 각 간선을 하나씩 끊어서 확인
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            // 간선을 잠시 끊음
            graph.get(a).remove(Integer.valueOf(b));
            graph.get(b).remove(Integer.valueOf(a));

            // DFS로 한쪽 네트워크의 노드 수 세기
            boolean[] visited = new boolean[n + 1];
            int countA = dfs(a, graph, visited);
            int countB = n - countA;

            int diff = Math.abs(countA - countB);
            minDiff = Math.min(minDiff, diff);

            // 간선 복원
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        return minDiff;
    }

    // DFS 탐색으로 연결된 송전탑 개수 세기
    private int dfs(int node, List<List<Integer>> graph, boolean[] visited) {
        visited[node] = true;
        int count = 1;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                count += dfs(next, graph, visited);
            }
        }

        return count;
    }
}
