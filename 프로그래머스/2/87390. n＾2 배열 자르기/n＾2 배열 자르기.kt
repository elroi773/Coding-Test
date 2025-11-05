class Solution {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        val size = (right - left + 1).toInt()
        val answer = IntArray(size)

        for (i in 0 until size) {
            val idx = left + i
            val row = idx / n
            val col = idx % n
            answer[i] = (if (row > col) row else col).toInt() + 1
        }

        return answer
    }
}
