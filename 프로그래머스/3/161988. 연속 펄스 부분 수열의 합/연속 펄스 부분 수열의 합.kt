class Solution {
    fun solution(sequence: IntArray): Long {
        var maxSum = 0L

        var curr1 = 0L // [1, -1, 1, -1 ...]
        var curr2 = 0L // [-1, 1, -1, 1 ...]

        for (i in sequence.indices) {
            val sign1 = if (i % 2 == 0) 1 else -1
            val sign2 = -sign1

            val v1 = sequence[i].toLong() * sign1
            val v2 = sequence[i].toLong() * sign2

            curr1 = maxOf(v1, curr1 + v1)
            curr2 = maxOf(v2, curr2 + v2)

            maxSum = maxOf(maxSum, curr1, curr2)
        }

        return maxSum
    }
}
