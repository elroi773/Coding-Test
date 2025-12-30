import Foundation

func solution(_ targets: [[Int]]) -> Int {
    // e 오름차순 정렬
    let sorted = targets.sorted { a, b in
        if a[1] == b[1] { return a[0] < b[0] }
        return a[1] < b[1]
    }

    var answer = 0
    var lastE = -1  // 직전에 발사한 요격점의 기준이 되는 e

    for t in sorted {
        let s = t[0]
        let e = t[1]

        // 개구간 (s,e)이므로 s < x < e
        // 이전에 e-ε에 쐈다고 보면, s >= lastE 이면 더 이상 겹치지 않음 -> 새 발사 필요
        if s >= lastE {
            answer += 1
            lastE = e
        }
    }

    return answer
}