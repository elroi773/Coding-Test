class Solution {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        // 1. 정렬 (col 오름차순, 첫 번째 컬럼 내림차순)
        val sorted = data.sortedWith(
            compareBy<IntArray> { it[col - 1] }.thenByDescending { it[0] }
        )

        // 2. S_i 계산 및 XOR 누적
        var answer = 0
        for (i in row_begin..row_end) {
            val row = sorted[i - 1]
            val S_i = row.sumOf { it % i }
            answer = answer xor S_i
        }

        return answer
    }
}
