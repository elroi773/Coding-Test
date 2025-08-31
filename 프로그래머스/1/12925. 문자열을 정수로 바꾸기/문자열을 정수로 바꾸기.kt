class Solution {
    fun solution(s: String): Int {
        var sign = 1
        var start = 0
        var answer = 0

        if (s[0] == '-') {
            sign = -1
            start = 1
        } else if (s[0] == '+') {
            start = 1
        }

        for (i in start until s.length) {
            answer = answer * 10 + (s[i] - '0')
        }

        return sign * answer
    }
}
