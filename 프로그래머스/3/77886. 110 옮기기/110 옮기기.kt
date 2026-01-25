class Solution {
    fun solution(s: Array<String>): Array<String> {
        val answer = Array(s.size) { "" }

        for (idx in s.indices) {
            val str = s[idx]
            val stack = StringBuilder(str.length)
            var cnt110 = 0

            // 1) "110" 전부 제거(스택 역할)
            for (ch in str) {
                stack.append(ch)
                val n = stack.length
                if (n >= 3 &&
                    stack[n - 3] == '1' &&
                    stack[n - 2] == '1' &&
                    stack[n - 1] == '0'
                ) {
                    stack.setLength(n - 3)
                    cnt110++
                }
            }

            // 2) 삽입 위치: 남은 문자열에서 마지막 '0' 뒤(없으면 0)
            var insertIdx = 0
            for (i in stack.length - 1 downTo 0) {
                if (stack[i] == '0') {
                    insertIdx = i + 1
                    break
                }
            }

            // 3) 결과 조립: prefix + ("110"*cnt110) + suffix
            val res = StringBuilder(stack.length + cnt110 * 3)
            res.append(stack.substring(0, insertIdx))
            repeat(cnt110) { res.append("110") }
            res.append(stack.substring(insertIdx))

            answer[idx] = res.toString()
        }

        return answer
    }
}