import Foundation

func solution(_ n: Int, _ results: [[Int]]) -> Int {
    // win[a][b] = a가 b를 이김
    var win = Array(repeating: Array(repeating: false, count: n + 1), count: n + 1)

    for r in results {
        let a = r[0], b = r[1]
        win[a][b] = true
    }

    // Floyd-Warshall: a>k and k>b => a>b
    for k in 1...n {
        for a in 1...n {
            if !win[a][k] { continue }
            for b in 1...n {
                if win[k][b] { win[a][b] = true }
            }
        }
    }

    var answer = 0
    for i in 1...n {
        var known = 0
        for j in 1...n {
            if i == j { continue }
            if win[i][j] || win[j][i] { known += 1 }
        }
        if known == n - 1 { answer += 1 }
    }

    return answer
}