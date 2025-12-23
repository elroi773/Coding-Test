class Solution {
    fun solution(n: Int): Int {
        val MOD = 1234567

        var a = 0  // F(0)
        var b = 1  // F(1)

        for (i in 2..n) {
            val next = (a + b) % MOD
            a = b
            b = next
        }

        return b
    }
}
