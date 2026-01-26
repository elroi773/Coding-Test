import kotlin.math.abs

class Solution {
    fun solution(a: IntArray, edges: Array<IntArray>): Long {
        val n = a.size

        var sum = 0L
        for (x in a) sum += x.toLong()
        if (sum != 0L) return -1L

        // 인접 리스트 (head / to / next)
        val m = edges.size
        val E = 2 * m
        val head = IntArray(n) { -1 }
        val to = IntArray(E)
        val next = IntArray(E)

        var idx = 0
        for (e in edges) {
            val u = e[0]
            val v = e[1]

            to[idx] = v
            next[idx] = head[u]
            head[u] = idx
            idx++

            to[idx] = u
            next[idx] = head[v]
            head[v] = idx
            idx++
        }

        // parent + DFS order (stack)
        val parent = IntArray(n) { -1 }
        val order = IntArray(n)
        var ordSz = 0

        val stack = IntArray(n)
        var top = 0

        stack[top++] = 0
        parent[0] = 0

        while (top > 0) {
            val u = stack[--top]
            order[ordSz++] = u

            var e = head[u]
            while (e != -1) {
                val v = to[e]
                if (parent[v] == -1) {
                    parent[v] = u
                    stack[top++] = v
                }
                e = next[e]
            }
        }

        // 값은 Long으로 관리
        val valArr = LongArray(n) { i -> a[i].toLong() }

        // 후위 처리(역순), 루트(0) 제외
        var ans = 0L
        for (i in ordSz - 1 downTo 1) {
            val u = order[i]
            val x = valArr[u]
            ans += kotlin.math.abs(x)
            val p = parent[u]
            valArr[p] += x
            valArr[u] = 0L
        }

        return ans
    }
}