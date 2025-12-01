class Solution {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        // 행렬 초기화
        val matrix = Array(rows) { i -> IntArray(columns) { j -> i * columns + j + 1 } }
        val answer = mutableListOf<Int>()

        for (query in queries) {
            val x1 = query[0] - 1
            val y1 = query[1] - 1
            val x2 = query[2] - 1
            val y2 = query[3] - 1

            var prev = matrix[x1][y1]
            var minVal = prev

            // 위쪽 행 (왼쪽 → 오른쪽)
            for (j in y1 + 1..y2) {
                val temp = matrix[x1][j]
                matrix[x1][j] = prev
                prev = temp
                minVal = minOf(minVal, prev)
            }

            // 오른쪽 열 (위 → 아래)
            for (i in x1 + 1..x2) {
                val temp = matrix[i][y2]
                matrix[i][y2] = prev
                prev = temp
                minVal = minOf(minVal, prev)
            }

            // 아래쪽 행 (오른쪽 → 왼쪽)
            for (j in y2 - 1 downTo y1) {
                val temp = matrix[x2][j]
                matrix[x2][j] = prev
                prev = temp
                minVal = minOf(minVal, prev)
            }

            // 왼쪽 열 (아래 → 위)
            for (i in x2 - 1 downTo x1) {
                val temp = matrix[i][y1]
                matrix[i][y1] = prev
                prev = temp
                minVal = minOf(minVal, prev)
            }

            answer.add(minVal)
        }

        return answer.toIntArray()
    }
}