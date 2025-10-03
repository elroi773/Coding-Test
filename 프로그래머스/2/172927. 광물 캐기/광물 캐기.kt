class Solution {
    fun solution(picks: IntArray, minerals: Array<String>): Int {
        val fatigue = arrayOf(
            intArrayOf(1, 1, 1),    // 다이아 곡괭이
            intArrayOf(5, 1, 1),    // 철 곡괭이
            intArrayOf(25, 5, 1)    // 돌 곡괭이
        )

        fun mineralToInt(m: String): Int = when (m) {
            "diamond" -> 0
            "iron" -> 1
            else -> 2
        }

        val totalPicks = picks.sum()
        var groupCount = (minerals.size + 4) / 5
        if (groupCount > totalPicks) groupCount = totalPicks

        // 1) 그룹화
        val groups = mutableListOf<IntArray>()
        for (i in 0 until groupCount) {
            val slice = minerals.slice(i * 5 until minOf((i + 1) * 5, minerals.size))
                .map { mineralToInt(it) }
                .toIntArray()
            groups.add(slice)
        }

        // 2) 그룹 난이도 계산 (돌 곡괭이로 캤을 때 피로도) → 정렬
        groups.sortByDescending { g ->
            g.sumOf { fatigue[2][it] }
        }

        // 3) 곡괭이 리스트 (좋은 곡괭이부터)
        val pickList = mutableListOf<Int>()
        for (i in 0..2) {
            repeat(picks[i]) { pickList.add(i) }
        }
        pickList.sort()

        // 4) 피로도 계산
        var answer = 0
        for (i in groups.indices) {
            val pick = pickList[i]
            for (m in groups[i]) {
                answer += fatigue[pick][m]
            }
        }

        return answer
    }
}
