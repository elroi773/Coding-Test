class Solution {
    fun solution(my_string: String, m: Int, c: Int): String {
        var answer = StringBuilder()
        val rows = my_string.length / m

        for (i in 0 until rows) {
            val index = i * m + (c - 1)
            answer.append(my_string[index])
        }

        return answer.toString()
    }
}
