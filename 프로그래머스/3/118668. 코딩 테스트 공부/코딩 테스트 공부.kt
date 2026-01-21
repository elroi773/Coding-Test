class Solution {
    fun solution(alp: Int, cop: Int, problems: Array<IntArray>): Int {
        var maxAlp = 0
        var maxCop = 0
        for (p in problems) {
            maxAlp = maxOf(maxAlp, p[0])
            maxCop = maxOf(maxCop, p[1])
        }

        val startAlp = minOf(alp, maxAlp)
        val startCop = minOf(cop, maxCop)

        val INF = 1_000_000_000
        val dp = Array(maxAlp + 1) { IntArray(maxCop + 1) { INF } }
        dp[startAlp][startCop] = 0

        for (a in startAlp..maxAlp) {
            for (c in startCop..maxCop) {
                val cur = dp[a][c]
                if (cur == INF) continue

                if (a + 1 <= maxAlp) dp[a + 1][c] = minOf(dp[a + 1][c], cur + 1)
                if (c + 1 <= maxCop) dp[a][c + 1] = minOf(dp[a][c + 1], cur + 1)

                for (p in problems) {
                    val reqA = p[0]; val reqC = p[1]
                    val rwdA = p[2]; val rwdC = p[3]
                    val cost = p[4]
                    if (a >= reqA && c >= reqC) {
                        val na = minOf(maxAlp, a + rwdA)
                        val nc = minOf(maxCop, c + rwdC)
                        dp[na][nc] = minOf(dp[na][nc], cur + cost)
                    }
                }
            }
        }

        return dp[maxAlp][maxCop]
    }
}