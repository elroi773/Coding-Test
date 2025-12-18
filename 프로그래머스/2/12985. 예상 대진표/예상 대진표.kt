class Solution {
    fun solution(n: Int, a: Int, b: Int): Int {
        var A = a
        var B = b
        var round = 0

        while (A != B) {
            A = (A + 1) / 2
            B = (B + 1) / 2
            round++
        }

        return round
    }
}
