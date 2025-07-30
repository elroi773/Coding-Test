class Solution {
    fun solution(a: Int, b: Int): Long {
        val start = minOf(a, b)
        val end = maxOf(a, b)
        var sum: Long = 0

        for (i in start..end) {
            sum += i
        }

        return sum
    }
}
