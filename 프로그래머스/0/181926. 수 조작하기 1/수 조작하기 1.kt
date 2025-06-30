class Solution {
    fun solution(n: Int, control: String): Int {
        var result = n

        for (ch in control) {
            when (ch) {
                'w' -> result += 1
                's' -> result -= 1
                'd' -> result += 10
                'a' -> result -= 10
            }
        }

        return result
    }
}
