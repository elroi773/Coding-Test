class Solution {
    fun solution(clockHands: Array<IntArray>): Int {
        val n = clockHands.size
        var best = Int.MAX_VALUE

        fun press(b: Array<IntArray>, r: Int, c: Int, t: Int) {
            if (t == 0) return
            b[r][c] = (b[r][c] + t) and 3
            if (r > 0) b[r - 1][c] = (b[r - 1][c] + t) and 3
            if (r < n - 1) b[r + 1][c] = (b[r + 1][c] + t) and 3
            if (c > 0) b[r][c - 1] = (b[r][c - 1] + t) and 3
            if (c < n - 1) b[r][c + 1] = (b[r][c + 1] + t) and 3
        }

        // 4^n = 2^(2n): 첫 행 각 칸(0~3회)을 2비트씩으로 표현
        val cases = 1 shl (2 * n)

        for (mask in 0 until cases) {
            // board 복사
            val b = Array(n) { i -> clockHands[i].clone() }

            var cnt = 0
            var tmp = mask

            // 1) 첫 행 조작 적용
            for (c in 0 until n) {
                val t = tmp and 3   // 0~3
                tmp = tmp shr 2
                cnt += t
                press(b, 0, c, t)
            }

            // 2) 2행~n행: 윗칸을 0으로 만들도록 강제 조작
            for (r in 1 until n) {
                for (c in 0 until n) {
                    val above = b[r - 1][c]
                    if (above != 0) {
                        val t = (4 - above) and 3 // 1~3
                        cnt += t
                        press(b, r, c, t)
                    }
                }
            }

            // 3) 마지막 행이 모두 0이면 성공
            var ok = true
            for (c in 0 until n) {
                if (b[n - 1][c] != 0) { ok = false; break }
            }

            if (ok) best = minOf(best, cnt)
        }

        return best
    }
}