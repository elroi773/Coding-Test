import Foundation

func solution(_ numbers: String) -> Int {
    let INF = 1_000_000_000

    // digit -> (x,y)
    var x = Array(repeating: 0, count: 10)
    var y = Array(repeating: 0, count: 10)

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

    // dist: move-only shortest cost (same digit = 0)
    var dist = Array(repeating: Array(repeating: INF, count: 10), count: 10)
    for i in 0..<10 { dist[i][i] = 0 }

    // adjacency: orthogonal=2, diagonal=3
    for a in 0..<10 {
        for b in (a + 1)..<10 {
            let dx = abs(x[a] - x[b])
            let dy = abs(y[a] - y[b])
            if dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0) {
                let w = (dx == 0 || dy == 0) ? 2 : 3
                dist[a][b] = w
                dist[b][a] = w
            }
        }
    }

    // Floyd-Warshall
    for k in 0..<10 {
        for i in 0..<10 {
            let dik = dist[i][k]
            if dik >= INF { continue }
            for j in 0..<10 {
                let nd = dik + dist[k][j]
                if nd < dist[i][j] { dist[i][j] = nd }
            }
        }
    }

    // cost[a][b] = press cost (a==b => 1)
    var cost = Array(repeating: Array(repeating: 0, count: 10), count: 10)
    for a in 0..<10 {
        for b in 0..<10 {
            cost[a][b] = (a == b) ? 1 : dist[a][b]
        }
    }

    // dp[l][r], l != r
    var dp = Array(repeating: Array(repeating: INF, count: 10), count: 10)
    dp[4][6] = 0 // start

    for scalar in numbers.unicodeScalars {
        let d = Int(scalar.value) - 48
        var ndp = Array(repeating: Array(repeating: INF, count: 10), count: 10)

        for l in 0..<10 {
            for r in 0..<10 {
                if l == r { continue }
                let cur = dp[l][r]
                if cur >= INF { continue }

                if d == l {
                    ndp[l][r] = min(ndp[l][r], cur + 1) // must press with left
                } else if d == r {
                    ndp[l][r] = min(ndp[l][r], cur + 1) // must press with right
                } else {
                    // press with left -> (d, r)
                    if d != r {
                        ndp[d][r] = min(ndp[d][r], cur + cost[l][d])
                    }
                    // press with right -> (l, d)
                    if d != l {
                        ndp[l][d] = min(ndp[l][d], cur + cost[r][d])
                    }
                }
            }
        }
        dp = ndp
    }

    var ans = INF
    for l in 0..<10 {
        for r in 0..<10 where l != r {
            ans = min(ans, dp[l][r])
        }
    }
    return ans
}