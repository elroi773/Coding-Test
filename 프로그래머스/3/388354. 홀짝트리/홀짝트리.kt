import java.util.ArrayDeque
import java.util.ArrayList

class Solution {
    fun solution(nodes: IntArray, edges: Array<IntArray>): IntArray {

        val MAX = 1_000_000

        // 인접 리스트
        val graph = Array(MAX + 1) { ArrayList<Int>() }
        val degree = IntArray(MAX + 1)
        val visited = BooleanArray(MAX + 1)

        // 간선 정보 처리
        for (e in edges) {
            val a = e[0]
            val b = e[1]
            graph[a].add(b)
            graph[b].add(a)
            degree[a]++
            degree[b]++
        }

        var oddEvenTree = 0
        var reverseTree = 0

        val queue: ArrayDeque<Int> = ArrayDeque()

        // 각 트리(BFS)
        for (start in nodes) {
            if (visited[start]) continue

            queue.clear()
            queue.add(start)
            visited[start] = true

            var A = 0
            var B = 0

            while (queue.isNotEmpty()) {
                val cur = queue.poll()

                if ((degree[cur] and 1) == (cur and 1)) A++
                else B++

                for (next in graph[cur]) {
                    if (!visited[next]) {
                        visited[next] = true
                        queue.add(next)
                    }
                }
            }

            if (A == 1) oddEvenTree++
            if (B == 1) reverseTree++
        }

        return intArrayOf(oddEvenTree, reverseTree)
    }
}
