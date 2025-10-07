class Solution {
    fun solution(m: Int, n: Int, startX: Int, startY: Int, balls: Array<IntArray>): IntArray {
        val answer = IntArray(balls.size)

        for (i in balls.indices) {
            val (a, b) = balls[i]
            var minDist = Int.MAX_VALUE

            // 1️⃣ 위쪽 벽 반사 (y → 2n - b)
            if (!(startX == a && startY < b)) {
                val y = 2 * n - b
                val dist = distSq(startX, startY, a, y)
                minDist = minOf(minDist, dist)
            }

            // 2️⃣ 아래쪽 벽 반사 (y → -b)
            if (!(startX == a && startY > b)) {
                val y = -b
                val dist = distSq(startX, startY, a, y)
                minDist = minOf(minDist, dist)
            }

            // 3️⃣ 왼쪽 벽 반사 (x → -a)
            if (!(startY == b && startX > a)) {
                val x = -a
                val dist = distSq(startX, startY, x, b)
                minDist = minOf(minDist, dist)
            }

            // 4️⃣ 오른쪽 벽 반사 (x → 2m - a)
            if (!(startY == b && startX < a)) {
                val x = 2 * m - a
                val dist = distSq(startX, startY, x, b)
                minDist = minOf(minDist, dist)
            }

            answer[i] = minDist
        }

        return answer
    }

    // 거리의 제곱 계산 함수
    private fun distSq(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        val dx = x1 - x2
        val dy = y1 - y2
        return dx * dx + dy * dy
    }
}
