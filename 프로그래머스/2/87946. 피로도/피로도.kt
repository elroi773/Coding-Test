class Solution {
    var maxCount = 0 // 최대 탐험 가능한 던전 수 저장

    fun solution(k: Int, dungeons: Array<IntArray>): Int {
        val visited = BooleanArray(dungeons.size)
        dfs(k, dungeons, visited, 0)
        return maxCount
    }

    private fun dfs(fatigue: Int, dungeons: Array<IntArray>, visited: BooleanArray, count: Int) {
        // 현재까지 탐험한 던전 수로 최대값 갱신
        if (count > maxCount) maxCount = count

        for (i in dungeons.indices) {
            val need = dungeons[i][0] // 최소 필요 피로도
            val cost = dungeons[i][1] // 소모 피로도

            if (!visited[i] && fatigue >= need) {
                visited[i] = true
                dfs(fatigue - cost, dungeons, visited, count + 1)
                visited[i] = false // 백트래킹
            }
        }
    }
}
