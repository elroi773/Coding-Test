import Foundation

func solution(_ n: Int, _ times: [Int]) -> Int64 {
    // 정렬은 필수는 아니지만 upper bound 계산에 편함
    let times64 = times.map { Int64($0) }.sorted()
    let n64 = Int64(n)

    var left: Int64 = 1
    var right: Int64 = times64.last! * n64   // 최악: 가장 느린 심사관 1명이 n명 처리
    var answer: Int64 = right

    while left <= right {
        let mid = (left + right) / 2

        var processed: Int64 = 0
        for t in times64 {
            processed += mid / t
            if processed >= n64 { break } // 오버플로/불필요 계산 방지
        }

        if processed >= n64 {
            answer = mid
            right = mid - 1
        } else {
            left = mid + 1
        }
    }

    return answer
}