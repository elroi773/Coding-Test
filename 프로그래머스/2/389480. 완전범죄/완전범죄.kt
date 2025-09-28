class Solution {
    fun solution(info: Array<IntArray>, n: Int, m: Int): Int {
        val INF = Int.MAX_VALUE / 4
        var dp = IntArray(m) { INF }
        dp[0] = 0

        for (item in info) {
            val aCost = item[0]
            val bCost = item[1]
            val newdp = IntArray(m) { INF }

            for (b in 0 until m) {
                if (dp[b] == INF) continue

                // A가 훔치는 경우
                val newA = dp[b] + aCost
                if (newA < n) {
                    newdp[b] = minOf(newdp[b], newA)
                }

                // B가 훔치는 경우
                val newB = b + bCost
                if (newB < m) {
                    newdp[newB] = minOf(newdp[newB], dp[b])
                }
            }
            dp = newdp
        }

        val ans = dp.minOrNull() ?: INF
        return if (ans == INF) -1 else ans
    }
}
