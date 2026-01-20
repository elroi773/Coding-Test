import java.util.*;

class Solution {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }
    static class State {
        int node, inten;
        State(int node, int inten) { this.node = node; this.inten = inten; }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        List<Edge>[] g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) g[i] = new ArrayList<>();
        for (int[] p : paths) {
            int a = p[0], b = p[1], w = p[2];
            g[a].add(new Edge(b, w));
            g[b].add(new Edge(a, w));
        }

        boolean[] isGate = new boolean[n + 1];
        boolean[] isSummit = new boolean[n + 1];
        for (int x : gates) isGate[x] = true;
        for (int x : summits) isSummit[x] = true;

        int INF = Integer.MAX_VALUE;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<State> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.inten));
        for (int gate : gates) {
            dist[gate] = 0;
            pq.offer(new State(gate, 0));
        }

        int bestIntensity = INF;

        while (!pq.isEmpty()) {
            State cur = pq.poll();
            int u = cur.node, inten = cur.inten;
            if (inten != dist[u]) continue;
            if (inten > bestIntensity) break;

            if (isSummit[u]) {
                bestIntensity = Math.min(bestIntensity, inten);
                continue; // do not expand from summit
            }

            for (Edge e : g[u]) {
                int v = e.to;
                if (isGate[v]) continue;
                int ni = Math.max(inten, e.w);
                if (ni < dist[v]) {
                    dist[v] = ni;
                    pq.offer(new State(v, ni));
                }
            }
        }

        Arrays.sort(summits);
        int ansSummit = summits[0];
        int ansInten = dist[ansSummit];
        for (int s : summits) {
            if (dist[s] < ansInten) {
                ansInten = dist[s];
                ansSummit = s;
            }
        }
        return new int[]{ansSummit, ansInten};
    }
}