class Solution {
    fun solution(s: String): String {
        val len = s.length
        val mid = len / 2

        return if (len % 2 == 0) {
            s.substring(mid - 1, mid + 1) // 짝수일 때 가운데 두 글자
        } else {
            s[mid].toString() // 홀수일 때 가운데 한 글자
        }
    }
}
