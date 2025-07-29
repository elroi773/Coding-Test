class Solution {
    fun solution(number: Int, limit: Int, power: Int): Int {
        var answer = 0

        for (i in 1..number) {
            val divisorCount = countDivisors(i)
            answer += if (divisorCount > limit) power else divisorCount
        }

        return answer
    }

    private fun countDivisors(n: Int): Int {
        var count = 0
        var i = 1
        while (i * i <= n) {
            if (n % i == 0) {
                count += if (i * i == n) 1 else 2
            }
            i++
        }
        return count
    }
}
