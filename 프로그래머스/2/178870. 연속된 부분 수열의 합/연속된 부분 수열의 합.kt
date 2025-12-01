class Solution {
    fun solution(sequence: IntArray, k: Int): IntArray {
        val n = sequence.size
        var start = 0
        var end = 0
        var currentSum = sequence[0]

        var minLen = n
        var answer = intArrayOf(0, n - 1) // 초기값: 최대 길이 구간

        while (start < n && end < n) {
            when {
                currentSum < k -> {
                    end++
                    if (end < n) currentSum += sequence[end]
                }
                currentSum > k -> {
                    currentSum -= sequence[start]
                    start++
                }
                else -> { // currentSum == k
                    if ((end - start) < minLen) {
                        minLen = end - start
                        answer = intArrayOf(start, end)
                    }
                    currentSum -= sequence[start]
                    start++
                }
            }
        }

        return answer
    }
}