class Solution {
    fun solution(n: Int, wires: Array<IntArray>): Int {
        // 그래프를 인접 리스트로 생성
        val graph = Array(n + 1) { mutableListOf<Int>() }

        for (wire in wires) {
            val (a, b) = wire
            graph[a].add(b)
            graph[b].add(a)
        }

        var minDiff = n // 최대 차이로 초기화

        // 각 간선을 하나씩 끊어보기
        for (wire in wires) {
            val (a, b) = wire

            // 간선 끊기
            graph[a].remove(b)
            graph[b].remove(a)

            // DFS 탐색으로 한쪽 송전탑 개수 세기
            val visited = BooleanArray(n + 1)
            val countA = dfs(a, graph, visited)
            val countB = n - countA
            val diff = kotlin.math.abs(countA - countB)

            minDiff = minOf(minDiff, diff)

            // 간선 복원
            graph[a].add(b)
            graph[b].add(a)
        }

        return minDiff
    }

    // DFS로 연결된 송전탑 개수 세기
    private fun dfs(node: Int, graph: Array<MutableList<Int>>, visited: BooleanArray): Int {
        visited[node] = true
        var count = 1

        for (next in graph[node]) {
            if (!visited[next]) {
                count += dfs(next, graph, visited)
            }
        }

        return count
    }
}
