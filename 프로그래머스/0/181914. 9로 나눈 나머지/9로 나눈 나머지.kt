class Solution {
    fun solution(number: String): Int {
        var sum = 0
        for (ch in number) {
            sum += ch.digitToInt() // 또는 ch - '0'
        }
        return sum % 9
    }
}
