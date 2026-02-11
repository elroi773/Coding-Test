import java.util.ArrayDeque

class Solution {
    fun solution(n: Int, edge: Array<IntArray>): Int {
        // 1) 인접 리스트
        val g = Array(n + 1) { ArrayList<Int>() }
        for (e in edge) {
            val a = e[0]
            val b = e[1]
            g[a].add(b)
            g[b].add(a)
        }

        // 2) 거리 배열 (-1: 미방문)
        val dist = IntArray(n + 1) { -1 }
        dist[1] = 0

        // 3) BFS 큐 (ArrayDeque 또는 IntArray+head 방식)
        val q = IntArray(n + 1)
        var head = 0
        var tail = 0
        q[tail++] = 1

        while (head < tail) {
            val cur = q[head++]
            val nextDist = dist[cur] + 1
            for (nxt in g[cur]) {
                if (dist[nxt] == -1) {
                    dist[nxt] = nextDist
                    q[tail++] = nxt
                }
            }
        }

        // 4) 최대 거리 + 개수
        var maxDist = 0
        for (i in 1..n) {
            if (dist[i] > maxDist) maxDist = dist[i]
        }

        var answer = 0
        for (i in 1..n) {
            if (dist[i] == maxDist) answer++
        }

        return answer
    }
}
