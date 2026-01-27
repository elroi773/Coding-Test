class Solution {
    fun solution(n: Int, s: Int, a: Int, b: Int, fares: Array<IntArray>): Int {
        val INF = Long.MAX_VALUE / 4

        val dist = Array(n + 1) { LongArray(n + 1) { INF } }
        for (i in 1..n) dist[i][i] = 0L

        // 양방향 간선
        for (f in fares) {
            val c = f[0]
            val d = f[1]
            val cost = f[2].toLong()
            if (cost < dist[c][d]) {
                dist[c][d] = cost
                dist[d][c] = cost
            }
        }

        // Floyd-Warshall
        for (k in 1..n) {
            for (i in 1..n) {
                val ik = dist[i][k]
                if (ik == INF) continue
                for (j in 1..n) {
                    val nd = ik + dist[k][j]
                    if (nd < dist[i][j]) dist[i][j] = nd
                }
            }
        }

        var ans = INF
        for (k in 1..n) {
            val cost = dist[s][k] + dist[k][a] + dist[k][b]
            if (cost < ans) ans = cost
        }

        return ans.toInt()
    }
}