import Foundation

func solution(_ sequence: [Int], _ k: Int) -> [Int] {
    let n = sequence.count
    var left = 0
    var sum = 0

    var bestL = 0
    var bestR = n - 1
    var bestLen = Int.max

    for right in 0..<n {
        sum += sequence[right]

        while sum > k && left <= right {
            sum -= sequence[left]
            left += 1
        }

        while sum == k && left <= right {
            let len = right - left
            if len < bestLen || (len == bestLen && left < bestL) {
                bestLen = len
                bestL = left
                bestR = right
            }
            // 더 짧게 만들 수 있는지 확인
            sum -= sequence[left]
            left += 1
        }
    }

    return [bestL, bestR]
}