import Foundation

func solution(_ scores: [[Int]]) -> Int {
    let wanhoA = scores[0][0]
    let wanhoB = scores[0][1]
    let wanhoSum = wanhoA + wanhoB

    // (a 내림차순, b 오름차순)
    let sorted = scores.sorted { x, y in
        if x[0] != y[0] { return x[0] > y[0] }
        return x[1] < y[1]
    }

    var maxB = -1
    var rank = 1

    for s in sorted {
        let a = s[0], b = s[1]

        // 이전에 a가 더 큰(또는 같은) 사람들 중 b가 더 큰 사람이 존재 => 지배됨
        if b < maxB {
            if a == wanhoA && b == wanhoB {
                return -1
            }
            continue
        }

        // 인센티브 대상인 경우만 석차 계산에 반영
        if a + b > wanhoSum {
            rank += 1
        }

        if b > maxB { maxB = b }
    }

    return rank
}