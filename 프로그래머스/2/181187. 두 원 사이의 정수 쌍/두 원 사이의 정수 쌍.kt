import kotlin.math.*

class Solution {
    fun solution(r1: Int, r2: Int): Long {
        var answer = 0L

        val R1 = r1.toLong()
        val R2 = r2.toLong()
        val R1sq = R1 * R1
        val R2sq = R2 * R2

        // x >= 0 만 돌면서 대칭으로 개수 계산
        for (x in 0..r2) {
            val xx = x.toLong() * x

            // 바깥 원(r2)에서의 최대 y
            val maxY = floor(sqrt((R2sq - xx).toDouble())).toLong()

            // 안쪽 원(r1)에서의 최소 y
            val diff1 = R1sq - xx
            val minY = if (diff1 > 0L) {
                ceil(sqrt(diff1.toDouble())).toLong()
            } else {
                0L
            }

            if (maxY < minY) continue  // 이 x에서는 유효한 y 없음

            if (x == 0) {
                // y축 위 점들: (0, y), (0, -y) → y 하나당 2개
                answer += 2L * (maxY - minY + 1L)
            } else {
                if (minY == 0L) {
                    // y = 0: (±x, 0) → 2개
                    answer += 2L
                    // y >= 1: (±x, ±y) → y 하나당 4개
                    answer += 4L * maxY
                } else {
                    // y >= minY: (±x, ±y) → y 하나당 4개
                    answer += 4L * (maxY - minY + 1L)
                }
            }
        }

        return answer
    }
}