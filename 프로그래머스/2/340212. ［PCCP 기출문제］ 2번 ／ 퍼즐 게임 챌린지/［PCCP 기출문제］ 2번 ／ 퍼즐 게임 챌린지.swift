import Foundation

func solution(_ diffs: [Int], _ times: [Int], _ limit: Int64) -> Int {
    let n = diffs.count
    let maxDiff = diffs.max() ?? 1

    func can(_ level: Int) -> Bool {
        var total: Int64 = 0

        // 0번 퍼즐
        total += Int64(times[0])
        if total > limit { return false }

        if n == 1 { return total <= limit }

        for i in 1..<n {
            let diff = diffs[i]
            let tCur = Int64(times[i])
            if diff <= level {
                total += tCur
            } else {
                let x = Int64(diff - level)
                let tPrev = Int64(times[i - 1])
                total += (tCur + tPrev) * x + tCur
            }
            if total > limit { return false } // 오버 방지 + 조기 종료
        }
        return total <= limit
    }

    var lo = 1
    var hi = maxDiff  // 이 정도면 무조건 가능(틀림 0) -> 문제에서 항상 해답 존재

    while lo < hi {
        let mid = (lo + hi) / 2
        if can(mid) {
            hi = mid
        } else {
            lo = mid + 1
        }
    }
    return lo
}