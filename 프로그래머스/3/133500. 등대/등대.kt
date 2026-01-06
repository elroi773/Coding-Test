import kotlin.math.min

class Solution {
    fun solution(n: Int, lighthouse: Array<IntArray>): Int {
        // 인접 리스트
        val g = Array(n + 1) { IntArrayList() }
        for (e in lighthouse) {
            val a = e[0]
            val b = e[1]
            g[a].add(b)
            g[b].add(a)
        }

        val parent = IntArray(n + 1) { 0 }
        val order = IntArray(n)        // DFS 방문 순서
        val stack = IntArray(n)        // 반복 DFS 스택

        // 반복 DFS로 parent 세팅 + order 만들기
        var top = 0
        var idx = 0
        stack[top++] = 1
        parent[1] = -1

        while (top > 0) {
            val u = stack[--top]
            order[idx++] = u

            val adj = g[u]
            for (i in 0 until adj.size) {
                val v = adj[i]
                if (v == parent[u]) continue
                if (parent[v] != 0) continue // 이미 방문
                parent[v] = u
                stack[top++] = v
            }
        }

        // dp0[u]: u 끔(선택X), dp1[u]: u 켬(선택O)
        val dp0 = IntArray(n + 1)
        val dp1 = IntArray(n + 1)

        // 후위 순회 효과: order를 역순으로 DP 채우기
        for (i in n - 1 downTo 0) {
            val u = order[i]
            var off = 0   // dp0
            var on = 1    // dp1 (u 켬)

            val adj = g[u]
            for (j in 0 until adj.size) {
                val v = adj[j]
                if (v == parent[u]) continue
                off += dp1[v]                 // u off -> 자식은 반드시 on
                on += min(dp0[v], dp1[v])     // u on  -> 자식은 min 선택
            }

            dp0[u] = off
            dp1[u] = on
        }

        return min(dp0[1], dp1[1])
    }

    // Kotlin에서 ArrayList<Int> 쓰면 박싱 비용이 커질 수 있어, int 전용 리스트를 하나 둠
    private class IntArrayList(initialCap: Int = 4) {
        private var arr = IntArray(initialCap)
        var size = 0
            private set

        operator fun get(i: Int): Int = arr[i]

        fun add(v: Int) {
            if (size == arr.size) arr = arr.copyOf(arr.size * 2)
            arr[size++] = v
        }
    }
}