class Solution {
    fun solution(s: String, n: Int): String {
        val sb = StringBuilder()

        for (ch in s) {
            when {
                ch == ' ' -> sb.append(' ')
                ch in 'A'..'Z' -> {
                    sb.append(((ch - 'A' + n) % 26 + 'A'.code).toChar())
                }
                ch in 'a'..'z' -> {
                    sb.append(((ch - 'a' + n) % 26 + 'a'.code).toChar())
                }
            }
        }

        return sb.toString()
    }
}
