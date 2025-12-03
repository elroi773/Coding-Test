class Solution {
    fun solution(n: Int, info: IntArray): IntArray {
        val apeach = info
        val cur = IntArray(11)      // 현재 탐색 중인 라이언 배치
        var best: IntArray? = null  // 최적 배치
        var bestDiff = 0            // 최대 점수 차이

        fun dfs(idx: Int, arrowsLeft: Int) {
            // 마지막 점수(0점, idx == 10) 처리
            if (idx == 10) {
                // 남은 화살 전부 0점에 사용
                cur[10] = arrowsLeft

                var rScore = 0
                var aScore = 0

                for (i in 0..10) {
                    if (apeach[i] == 0 && cur[i] == 0) continue

                    val point = 10 - i
                    if (cur[i] > apeach[i]) {
                        rScore += point
                    } else {
                        aScore += point
                    }
                }

                val diff = rScore - aScore

                // 라이언이 이기지 못하면 무시
                if (diff <= 0) {
                    cur[10] = 0
                    return
                }

                // 더 큰 점수 차이면 무조건 갱신
                if (best == null || diff > bestDiff) {
                    bestDiff = diff
                    best = cur.clone()
                }
                // 점수 차이가 같으면 낮은 점수(뒤 인덱스) 많이 맞힌 쪽 선택
                else if (diff == bestDiff) {
                    val b = best!!
                    for (i in 10 downTo 0) {
                        if (cur[i] > b[i]) {
                            best = cur.clone()
                            break
                        } else if (cur[i] < b[i]) {
                            break
                        }
                    }
                }

                cur[10] = 0
                return
            }

            // 1) 현재 점수(10 - idx)를 따기 위해 어피치보다 1발 더 쏘는 경우
            val need = apeach[idx] + 1
            if (need <= arrowsLeft) {
                cur[idx] = need
                dfs(idx + 1, arrowsLeft - need)
                cur[idx] = 0  // 백트래킹
            }

            // 2) 현재 점수 포기
            dfs(idx + 1, arrowsLeft)
        }

        dfs(0, n)

        // 이길 수 있는 경우가 없으면 [-1]
        return best ?: intArrayOf(-1)
    }
}