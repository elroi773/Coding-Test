class Solution {
    fun solution(arr1: Array<IntArray>, arr2: Array<IntArray>): Array<IntArray> {
        val row = arr1.size
        val col = arr1[0].size
        val answer = Array(row) { IntArray(col) }

        for (i in 0 until row) {
            for (j in 0 until col) {
                answer[i][j] = arr1[i][j] + arr2[i][j]
            }
        }

        return answer
    }
}
