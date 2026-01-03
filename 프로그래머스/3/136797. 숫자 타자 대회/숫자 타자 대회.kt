class Solution {
    fun solution(numbers: String): Int {
        val INF = 1_000_000_000

        // digit -> (x,y)
        val x = IntArray(10)
        val y = IntArray(10)

        x[0] = 1; y[0] = 3
        x[1] = 0; y[1] = 0
        x[2] = 1; y[2] = 0
        x[3] = 2; y[3] = 0
        x[4] = 0; y[4] = 1
        x[5] = 1; y[5] = 1
        x[6] = 2; y[6] = 1
        x[7] = 0; y[7] = 2
        x[8] = 1; y[8] = 2
        x[9] = 2; y[9] = 2

        // dist: 이동만의 최단 비용 (같은 숫자는 0)
        val dist = Array(10) { i -> IntArray(10) { j -> if (i == j) 0 else INF } }

        // 인접 간선: 상하좌우=2, 대각=3
        for (a in 0..9) {
            for (b in a + 1..9) {
                val dx = kotlin.math.abs(x[a] - x[b])
                val dy = kotlin.math.abs(y[a] - y[b])
                if (dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0)) {
                    val w = if (dx == 0 || dy == 0) 2 else 3
                    dist[a][b] = w
                    dist[b][a] = w
                }
            }
        }

        // Floyd-Warshall
        for (k in 0..9) {
            for (i in 0..9) {
                for (j in 0..9) {
                    val nd = dist[i][k] + dist[k][j]
                    if (nd < dist[i][j]) dist[i][j] = nd
                }
            }
        }

        // cost[a][b] = a에서 b를 "누르는" 비용 (a==b이면 1)
        val cost = Array(10) { IntArray(10) }
        for (a in 0..9) {
            for (b in 0..9) {
                cost[a][b] = if (a == b) 1 else dist[a][b]
            }
        }

        // dp[l][r] (l!=r): 현재 왼손=l, 오른손=r일 때 최소 비용
        var dp = Array(10) { IntArray(10) { INF } }
        dp[4][6] = 0 // 시작 위치

        for (ch in numbers) {
            val d = ch - '0'
            val ndp = Array(10) { IntArray(10) { INF } }

            for (l in 0..9) {
                for (r in 0..9) {
                    if (l == r) continue
                    val cur = dp[l][r]
                    if (cur >= INF) continue

                    // 그 숫자 위에 손가락이 있으면 반드시 그 손가락으로 눌러야 함
                    if (d == l) {
                        if (cur + 1 < ndp[l][r]) ndp[l][r] = cur + 1
                    } else if (d == r) {
                        if (cur + 1 < ndp[l][r]) ndp[l][r] = cur + 1
                    } else {
                        // 왼손으로 누르기: (d, r)
                        if (d != r) {
                            val v = cur + cost[l][d]
                            if (v < ndp[d][r]) ndp[d][r] = v
                        }
                        // 오른손으로 누르기: (l, d)
                        if (d != l) {
                            val v = cur + cost[r][d]
                            if (v < ndp[l][d]) ndp[l][d] = v
                        }
                    }
                }
            }
            dp = ndp
        }

        var ans = INF
        for (l in 0..9) {
            for (r in 0..9) {
                if (l == r) continue
                if (dp[l][r] < ans) ans = dp[l][r]
            }
        }
        return ans
    }
}