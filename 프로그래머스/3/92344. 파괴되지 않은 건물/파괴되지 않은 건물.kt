class Solution {
    fun solution(board: Array<IntArray>, skill: Array<IntArray>): Int {
        val n = board.size
        val m = board[0].size

        // (n+1) x (m+1) 2D diff (누적합 안전하게 Long)
        val diff = Array(n + 1) { LongArray(m + 1) }

        // 스킬 4점 업데이트
        for (s in skill) {
            val type = s[0]
            val r1 = s[1]
            val c1 = s[2]
            val r2 = s[3]
            val c2 = s[4]
            val degree = s[5]

            val delta = if (type == 1) -degree.toLong() else degree.toLong()

            diff[r1][c1] += delta
            if (c2 + 1 <= m) diff[r1][c2 + 1] -= delta
            if (r2 + 1 <= n) diff[r2 + 1][c1] -= delta
            if (r2 + 1 <= n && c2 + 1 <= m) diff[r2 + 1][c2 + 1] += delta
        }

        // 가로 누적합
        for (r in 0..n) {
            var run = 0L
            for (c in 0..m) {
                run += diff[r][c]
                diff[r][c] = run
            }
        }

        // 세로 누적합
        for (c in 0..m) {
            var run = 0L
            for (r in 0..n) {
                run += diff[r][c]
                diff[r][c] = run
            }
        }

        // 최종 내구도 > 0 카운트
        var answer = 0
        for (r in 0 until n) {
            for (c in 0 until m) {
                val finalHp = board[r][c].toLong() + diff[r][c]
                if (finalHp > 0L) answer++
            }
        }

        return answer
    }
}