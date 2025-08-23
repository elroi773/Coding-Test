class Solution {
    fun solution(n: Int): Int {
        for (x in 1 until n) {
            if (n % x == 1) {
                return x
            }
        }
        return -1 // 실제로는 여기까지 오지 않음
    }
}

fun main() {
    val sol = Solution()
    println(sol.solution(10)) // 3
    println(sol.solution(12)) // 11
}
