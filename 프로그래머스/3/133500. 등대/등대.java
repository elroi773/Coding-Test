import java.util.*;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        // 인접 리스트
        List<Integer>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] e : lighthouse) {
            int a = e[0], b = e[1];
            g[a].add(b);
            g[b].add(a);
        }

        int[] parent = new int[n + 1];
        int[] order = new int[n]; // DFS 방문 순서
        Arrays.fill(parent, 0);

        // 반복 DFS로 parent 설정 + order 만들기
        int idx = 0;
        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 1;
        parent[1] = -1;

        while (top > 0) {
            int u = stack[--top];
            order[idx++] = u;

            for (int v : g[u]) {
                if (v == parent[u]) continue;
                if (parent[v] != 0) continue; // 이미 방문
                parent[v] = u;
                stack[top++] = v;
            }
        }

        // dp0[u]: u 끔, dp1[u]: u 켬
        int[] dp0 = new int[n + 1];
        int[] dp1 = new int[n + 1];

        // 후위 처리 (order 역순)
        for (int i = n - 1; i >= 0; i--) {
            int u = order[i];

            int off = 0; // dp0
            int on = 1;  // dp1 (u 켬)

            for (int v : g[u]) {
                if (v == parent[u]) continue;
                off += dp1[v];                 // u가 꺼져 있으면, 자식은 반드시 켜야 간선 커버
                on  += Math.min(dp0[v], dp1[v]); // u가 켜져 있으면, 자식은 최소 선택
            }

            dp0[u] = off;
            dp1[u] = on;
        }

        return Math.min(dp0[1], dp1[1]);
    }
}