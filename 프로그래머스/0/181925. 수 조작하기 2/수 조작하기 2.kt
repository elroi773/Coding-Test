class Solution {
    fun solution(numLog: IntArray): String {
        val sb = StringBuilder()

        for (i in 1 until numLog.size) {
            val diff = numLog[i] - numLog[i - 1]
            when (diff) {
                1 -> sb.append('w')
                -1 -> sb.append('s')
                10 -> sb.append('d')
                -10 -> sb.append('a')
            }
        }

        return sb.toString()
    }
}
