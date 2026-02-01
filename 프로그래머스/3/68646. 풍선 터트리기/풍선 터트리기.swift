import Foundation

func solution(_ a: [Int]) -> Int {
    let n = a.count
    if n <= 1 { return n }

    let INF = Int.max
    var rightMin = Array(repeating: INF, count: n)

    // rightMin[i] = min(a[i+1...n-1]) (없으면 INF)
    var cur = INF
    var i = n - 1
    while i >= 0 {
        rightMin[i] = cur
        if a[i] < cur { cur = a[i] }
        i -= 1
        if i < 0 { break } // Int 언더플로 방지
    }

    var answer = 0
    var leftMin = INF
    for idx in 0..<n {
        if a[idx] <= leftMin || a[idx] <= rightMin[idx] {
            answer += 1
        }
        if a[idx] < leftMin { leftMin = a[idx] }
    }

    return answer
}