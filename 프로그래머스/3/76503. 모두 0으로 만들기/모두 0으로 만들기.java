import java.util.*;

class Solution {
    public long solution(int[] a, int[][] edges) {
        int n = a.length;

        long sum = 0L;
        for (int x : a) sum += x;
        if (sum != 0L) return -1L;

        // 인접 리스트 (배열 기반: head/next/to)
        int m = edges.length;
        int E = 2 * m;

        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] to = new int[E];
        int[] next = new int[E];

        int idx = 0;
        for (int[] e : edges) {
            int u = e[0], v = e[1];

            to[idx] = v; next[idx] = head[u]; head[u] = idx++;
            to[idx] = u; next[idx] = head[v]; head[v] = idx++;
        }

        // parent + DFS order (stack)
        int[] parent = new int[n];
        Arrays.fill(parent, -1);
        int[] order = new int[n];
        int ordSz = 0;

        int[] stack = new int[n];
        int top = 0;

        stack[top++] = 0;
        parent[0] = 0;

        while (top > 0) {
            int u = stack[--top];
            order[ordSz++] = u;

            for (int e = head[u]; e != -1; e = next[e]) {
                int v = to[e];
                if (parent[v] == -1) {
                    parent[v] = u;
                    stack[top++] = v;
                }
            }
        }

        // 값은 long으로 관리
        long[] val = new long[n];
        for (int i = 0; i < n; i++) val[i] = a[i];

        // 후위 처리(역순), 루트(0) 제외
        long ans = 0L;
        for (int i = ordSz - 1; i >= 1; i--) {
            int u = order[i];
            long x = val[u];
            ans += Math.abs(x);
            int p = parent[u];
            val[p] += x;
            val[u] = 0L;
        }

        return ans;
    }
}