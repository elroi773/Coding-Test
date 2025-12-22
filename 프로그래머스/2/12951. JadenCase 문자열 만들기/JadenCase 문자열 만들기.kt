class Solution {
    fun solution(s: String): String {
        val sb = StringBuilder()
        var isStart = true  // 단어의 시작 여부

        for (c in s) {
            if (c == ' ') {
                sb.append(c)
                isStart = true
            } else {
                if (isStart) {
                    sb.append(c.uppercaseChar())
                    isStart = false
                } else {
                    sb.append(c.lowercaseChar())
                }
            }
        }
        return sb.toString()
    }
}
