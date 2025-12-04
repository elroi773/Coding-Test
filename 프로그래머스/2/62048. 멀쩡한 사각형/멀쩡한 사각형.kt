class Solution {
    fun solution(w: Int, h: Int): Long {
        val W = w.toLong()
        val H = h.toLong()
        val g = gcd(W, H)

        // 전체 정사각형 - (대각선이 지나가서 못 쓰는 정사각형)
        // 못 쓰는 정사각형 개수 = W + H - gcd(W, H)
        return W * H - (W + H - g)
    }

    private fun gcd(a: Long, b: Long): Long {
        var x = a
        var y = b
        while (y != 0L) {
            val r = x % y
            x = y
            y = r
        }
        return x
    }
}