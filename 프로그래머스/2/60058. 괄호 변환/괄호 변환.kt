class Solution {
    fun solution(p: String): String {
        return convert(p)
    }

    private fun convert(p: String): String {
        if (p.isEmpty()) return ""

        // 1. 균형잡힌 문자열 u, v 분리
        var balance = 0
        var idx = 0
        for (i in p.indices) {
            if (p[i] == '(') balance++
            else balance--

            if (balance == 0) {
                idx = i
                break
            }
        }

        val u = p.substring(0, idx + 1)
        val v = p.substring(idx + 1)

        // 2. u가 올바른 괄호 문자열이면 u + convert(v)
        if (isCorrect(u)) {
            return u + convert(v)
        }

        // 3. u가 올바르지 않으면 규칙에 따라 변환
        val sb = StringBuilder()
        sb.append("(")
        sb.append(convert(v))
        sb.append(")")

        // u의 앞뒤 제거 후 뒤집기
        for (i in 1 until u.length - 1) {
            sb.append(if (u[i] == '(') ')' else '(')
        }

        return sb.toString()
    }

    // 올바른 괄호 문자열인지 검사
    private fun isCorrect(s: String): Boolean {
        var count = 0
        for (c in s) {
            if (c == '(') count++
            else count--

            if (count < 0) return false
        }
        return count == 0
    }
}
