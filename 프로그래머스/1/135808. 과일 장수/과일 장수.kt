class Solution {
    fun solution(k: Int, m: Int, score: IntArray): Int {
        // 1. 점수 내림차순 정렬
        val sorted = score.sortedDescending()

        var total = 0

        // 2. m개씩 상자 포장 (남는 사과는 버림)
        for (i in 0 until sorted.size step m) {
            if (i + m <= sorted.size) {
                val box = sorted.slice(i until i + m)
                val minScore = box.minOrNull() ?: 0
                total += minScore * m
            }
        }

        return total
    }
}
