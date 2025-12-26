class Solution {
    fun solution(temperature: Int, t1: Int, t2: Int, a: Int, b: Int, onboard: IntArray): Int {
        val SHIFT = 10          // -10 -> 0, 40 -> 50
        val MIN_T = -10
        val MAX_T = 40
        val STATES = 51
        val INF = 1_000_000_000

        val lo = t1 + SHIFT
        val hi = t2 + SHIFT

        var dp = IntArray(STATES) { INF }
        var ndp = IntArray(STATES) { INF }
        dp[temperature + SHIFT] = 0 // 0분 실내온도 = 실외온도

        for (i in 0 until onboard.size - 1) {
            // i분에 승객 탑승이면 i분 시점 온도는 [t1, t2]여야 함
            if (onboard[i] == 1) {
                for (t in 0 until STATES) {
                    if (t < lo || t > hi) dp[t] = INF
                }
            }

            for (t in 0 until STATES) ndp[t] = INF

            for (idx in 0 until STATES) {
                val cost = dp[idx]
                if (cost >= INF) continue

                val cur = idx - SHIFT

                // 1) 에어컨 OFF: 실외온도 방향으로 1도 이동(또는 유지), 비용 0
                val nxt = when {
                    cur < temperature -> cur + 1
                    cur > temperature -> cur - 1
                    else -> cur
                }
                val nidx = nxt + SHIFT
                if (cost < ndp[nidx]) ndp[nidx] = cost

                // 2) 에어컨 ON & 희망온도 = 현재온도: 유지, 비용 b
                val keepCost = cost + b
                if (keepCost < ndp[idx]) ndp[idx] = keepCost

                // 3) 에어컨 ON & 희망온도 > 현재온도: +1, 비용 a
                if (cur < MAX_T) {
                    val upCost = cost + a
                    if (upCost < ndp[idx + 1]) ndp[idx + 1] = upCost
                }

                // 4) 에어컨 ON & 희망온도 < 현재온도: -1, 비용 a
                if (cur > MIN_T) {
                    val downCost = cost + a
                    if (downCost < ndp[idx - 1]) ndp[idx - 1] = downCost
                }
            }

            val tmp = dp
            dp = ndp
            ndp = tmp
        }

        // 마지막 시점도 탑승이면 온도 제한 적용
        if (onboard[onboard.size - 1] == 1) {
            for (t in 0 until STATES) {
                if (t < lo || t > hi) dp[t] = INF
            }
        }

        var ans = INF
        for (v in dp) if (v < ans) ans = v
        return ans
    }
}