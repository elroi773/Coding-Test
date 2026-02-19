import java.util.Arrays

class Solution {
    private class DSU(n: Int) {
        private val parent = IntArray(n) { it }
        private val rank = IntArray(n)

        private fun find(x: Int): Int {
            if (parent[x] == x) return x
            parent[x] = find(parent[x])
            return parent[x]
        }

        fun union(a: Int, b: Int): Boolean {
            var x = find(a)
            var y = find(b)
            if (x == y) return false

            if (rank[x] < rank[y]) {
                val tmp = x; x = y; y = tmp
            }
            parent[y] = x
            if (rank[x] == rank[y]) rank[x]++
            return true
        }
    }

    fun solution(n: Int, costs: Array<IntArray>): Int {
        // Kruskal: 비용 오름차순 정렬
        Arrays.sort(costs) { a, b -> a[2] - b[2] }

        val dsu = DSU(n)
        var answer = 0
        var picked = 0

        for (e in costs) {
            val u = e[0]
            val v = e[1]
            val w = e[2]

            if (dsu.union(u, v)) {
                answer += w
                picked++
                if (picked == n - 1) break
            }
        }

        return answer
    }
}