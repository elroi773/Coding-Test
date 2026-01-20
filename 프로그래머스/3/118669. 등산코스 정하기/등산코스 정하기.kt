import java.util.PriorityQueue
import kotlin.math.max

class Solution {
    data class Edge(val to: Int, val w: Int)
    data class State(val inten: Int, val node: Int)

    fun solution(n: Int, paths: Array<IntArray>, gates: IntArray, summits: IntArray): IntArray {
        val g = Array(n + 1) { ArrayList<Edge>() }
        for (p in paths) {
            val a = p[0]; val b = p[1]; val w = p[2]
            g[a].add(Edge(b, w))
            g[b].add(Edge(a, w))
        }

        val isGate = BooleanArray(n + 1)
        val isSummit = BooleanArray(n + 1)
        for (x in gates) isGate[x] = true
        for (x in summits) isSummit[x] = true

        val INF = Int.MAX_VALUE
        val dist = IntArray(n + 1) { INF }

        val pq = PriorityQueue<State>(compareBy { it.inten })
        for (gate in gates) {
            dist[gate] = 0
            pq.add(State(0, gate))
        }

        var bestIntensity = INF

        while (pq.isNotEmpty()) {
            val (inten, u) = pq.poll()
            if (inten != dist[u]) continue
            if (inten > bestIntensity) break

            if (isSummit[u]) {
                if (inten < bestIntensity) bestIntensity = inten
                continue
            }

            for (e in g[u]) {
                val v = e.to
                if (isGate[v]) continue
                val ni = max(inten, e.w)
                if (ni < dist[v]) {
                    dist[v] = ni
                    pq.add(State(ni, v))
                }
            }
        }

        summits.sort()
        var ansSummit = summits[0]
        var ansInten = dist[ansSummit]
        for (s in summits) {
            if (dist[s] < ansInten) {
                ansInten = dist[s]
                ansSummit = s
            }
        }
        return intArrayOf(ansSummit, ansInten)
    }
}