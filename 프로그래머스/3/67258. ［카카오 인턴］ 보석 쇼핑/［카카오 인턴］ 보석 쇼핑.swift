import Foundation

func solution(_ gems: [String]) -> [Int] {
    let need = Set(gems).count
    var count: [String: Int] = [:]

    var l = 0
    var bestL = 0
    var bestR = gems.count - 1
    var bestLen = bestR - bestL

    for r in 0..<gems.count {
        let g = gems[r]
        count[g, default: 0] += 1

        // 모든 종류를 포함하면 왼쪽을 가능한 한 줄이기
        while count.count == need && l <= r {
            let len = r - l
            if len < bestLen || (len == bestLen && l < bestL) {
                bestLen = len
                bestL = l
                bestR = r
            }

            let leftGem = gems[l]
            if let c = count[leftGem] {
                if c == 1 { count.removeValue(forKey: leftGem) }
                else { count[leftGem] = c - 1 }
            }
            l += 1
        }
    }

    // 진열대 번호는 1부터 시작
    return [bestL + 1, bestR + 1]
}