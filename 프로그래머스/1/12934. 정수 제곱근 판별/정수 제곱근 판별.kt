import kotlin.math.sqrt

class Solution {
    fun solution(n: Long): Long {
        val x = sqrt(n.toDouble()).toLong()

        return if (x * x == n) {
            (x + 1) * (x + 1)
        } else {
            -1
        }
    }
}
