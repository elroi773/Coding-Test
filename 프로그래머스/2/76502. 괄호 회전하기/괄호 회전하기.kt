class Solution {
    fun solution(s: String): Int {
        var answer = 0
        val n = s.length

        for (x in 0 until n) {
            val rotated = s.substring(x) + s.substring(0, x)
            if (isValid(rotated)) {
                answer++
            }
        }

        return answer
    }

    private fun isValid(str: String): Boolean {
        val stack = ArrayDeque<Char>()

        for (c in str) {
            when (c) {
                '(', '[', '{' -> stack.addLast(c)

                ')', ']', '}' -> {
                    if (stack.isEmpty()) return false
                    val top = stack.removeLast()

                    if ((c == ')' && top != '(') ||
                        (c == ']' && top != '[') ||
                        (c == '}' && top != '{')
                    ) return false
                }
            }
        }
        return stack.isEmpty()
    }
}
