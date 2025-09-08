class Solution {
    fun solution(s: String): Boolean {
        // 길이가 4 또는 6이 아닌 경우 false
        if (s.length != 4 && s.length != 6) return false

        // 모든 문자가 숫자인지 확인
        return s.all { it.isDigit() }
    }
}
