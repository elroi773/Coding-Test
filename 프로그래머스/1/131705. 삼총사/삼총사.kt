class Solution {
    fun solution(number: IntArray): Int {
        var answer = 0

        for (i in 0 until number.size - 2) {
            for (j in i + 1 until number.size - 1) {
                for (k in j + 1 until number.size) {
                    if (number[i] + number[j] + number[k] == 0) {
                        answer++
                    }
                }
            }
        }

        return answer
    }
}

fun main() {
    val sol = Solution()
    println(sol.solution(intArrayOf(-2, 3, 0, 2, -5)))      // 2
    println(sol.solution(intArrayOf(-3, -2, -1, 0, 1, 2, 3))) // 5
    println(sol.solution(intArrayOf(-1, 1, -1, 1)))         // 0
}
