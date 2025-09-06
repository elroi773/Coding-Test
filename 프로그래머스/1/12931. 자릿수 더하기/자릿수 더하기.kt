class Solution {
    fun solution(n: Int): Int {
        var answer = 0
        var num = n

        while (num > 0) {
            answer += num % 10
            num /= 10
        }

        return answer
    }
}

fun main() {
    val sol = Solution()
    println(sol.solution(123)) // 6
    println(sol.solution(987)) // 24
}
