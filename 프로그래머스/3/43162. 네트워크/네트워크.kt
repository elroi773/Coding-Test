class Solution {
    fun solution(n: Int, computers: Array<IntArray>): Int {
        val visited = BooleanArray(n)
        var answer = 0

        fun dfs(cur: Int) {
            visited[cur] = true
            for (next in 0 until n) {
                if (computers[cur][next] == 1 && !visited[next]) {
                    dfs(next)
                }
            }
        }

        for (i in 0 until n) {
            if (!visited[i]) {
                answer++
                dfs(i)
            }
        }

        return answer
    }
}