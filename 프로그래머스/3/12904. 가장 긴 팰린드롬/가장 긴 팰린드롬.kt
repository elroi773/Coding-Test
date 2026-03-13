class Solution {
    fun expand(s: String, leftStart: Int, rightStart: Int): Int {
        var left = leftStart
        var right = rightStart

        while (left >= 0 && right < s.length && s[left] == s[right]) {
            left--
            right++
        }

        return right - left - 1
    }

    fun solution(s: String): Int {
        var answer = 1

        for (i in s.indices) {
            val odd = expand(s, i, i)       // 홀수 길이
            val even = expand(s, i, i + 1)  // 짝수 길이

            answer = maxOf(answer, odd, even)
        }

        return answer
    }
}