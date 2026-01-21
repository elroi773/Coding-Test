import Foundation

func solution(_ alp:Int, _ cop:Int, _ problems:[[Int]]) -> Int {
    // 1) 목표치 설정
    var maxAlp = 0
    var maxCop = 0
    for p in problems {
        maxAlp = max(maxAlp, p[0])
        maxCop = max(maxCop, p[1])
    }

    // 시작점이 이미 목표 이상이면 목표로 클램프
    let startAlp = min(alp, maxAlp)
    let startCop = min(cop, maxCop)

    // 2) dp 초기화 (INF 크게)
    let INF = 1_000_000_000
    var dp = Array(
        repeating: Array(repeating: INF, count: maxCop + 1),
        count: maxAlp + 1
    )
    dp[startAlp][startCop] = 0

    // 3) 상태 순회
    for a in startAlp...maxAlp {
        for c in startCop...maxCop {
            let cur = dp[a][c]
            if cur == INF { continue }

            // 공부: 알고력 +1
            if a + 1 <= maxAlp {
                dp[a + 1][c] = min(dp[a + 1][c], cur + 1)
            }
            // 공부: 코딩력 +1
            if c + 1 <= maxCop {
                dp[a][c + 1] = min(dp[a][c + 1], cur + 1)
            }

            // 문제 풀기
            for p in problems {
                let reqA = p[0], reqC = p[1]
                let rwdA = p[2], rwdC = p[3]
                let cost = p[4]

                if a >= reqA && c >= reqC {
                    let na = min(maxAlp, a + rwdA)
                    let nc = min(maxCop, c + rwdC)
                    dp[na][nc] = min(dp[na][nc], cur + cost)
                }
            }
        }
    }

    return dp[maxAlp][maxCop]
}