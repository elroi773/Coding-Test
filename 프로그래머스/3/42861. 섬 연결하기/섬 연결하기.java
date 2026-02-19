import java.util.Arrays;

class Solution {
    static class DSU {
        int[] parent;
        int[] rank;

        DSU(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) parent[i] = i;
        }

        int find(int x) {
            if (parent[x] == x) return x;
            parent[x] = find(parent[x]);
            return parent[x];
        }

        boolean union(int a, int b) {
            a = find(a);
            b = find(b);
            if (a == b) return false;

            if (rank[a] < rank[b]) {
                parent[a] = b;
            } else if (rank[a] > rank[b]) {
                parent[b] = a;
            } else {
                parent[b] = a;
                rank[a]++;
            }
            return true;
        }
    }

    public int solution(int n, int[][] costs) {
        // Kruskal: 비용 오름차순 정렬
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));

        DSU dsu = new DSU(n);
        int answer = 0;
        int picked = 0;

        for (int[] e : costs) {
            int u = e[0], v = e[1], w = e[2];
            if (dsu.union(u, v)) {
                answer += w;
                picked++;
                if (picked == n - 1) break; // MST 완성
            }
        }

        return answer;
    }
}