import Foundation

func solution(_ info: [[Int]], _ n: Int, _ m: Int) -> Int {
    let INF = 1_000_000_000
    // A 흔적 합은 0...(n-1)까지만 허용
    var dp = Array(repeating: INF, count: n)
    dp[0] = 0

    for item in info {
        let aCost = item[0]
        let bCost = item[1]
        var next = Array(repeating: INF, count: n)

        for aSum in 0..<n {
            let bSum = dp[aSum]
            if bSum == INF { continue }

            // 1) A가 훔치는 경우: A 흔적 증가
            let na = aSum + aCost
            if na < n {
                next[na] = min(next[na], bSum)
            }

            // 2) B가 훔치는 경우: B 흔적 증가
            let nb = bSum + bCost
            if nb < m {
                next[aSum] = min(next[aSum], nb)
            }
        }
        dp = next
    }

    for aSum in 0..<n {
        if dp[aSum] < m {
            return aSum
        }
    }
    return -1
}