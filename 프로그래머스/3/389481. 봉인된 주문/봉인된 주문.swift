import Foundation

func solution(_ n: Int64, _ bans: [String]) -> String {
    // 최대 길이 11
    let MAXL = 11
    let INF: Int64 = Int64.max / 4

    // 26^k
    var pow = Array(repeating: Int64(1), count: MAXL + 1)
    for i in 1...MAXL {
        pow[i] = pow[i - 1] * 26
    }

    // prefix[l] = sum_{k=1..l} 26^k  (l=0이면 0)
    var prefix = Array(repeating: Int64(0), count: MAXL + 1)
    for l in 1...MAXL {
        prefix[l] = prefix[l - 1] + pow[l]
    }
    let total = prefix[MAXL]   // 전체 문자열 개수 (길이 1..11)

    // 문자열 -> 원래 주문서에서의 rank(1-indexed)
    func toRank(_ s: String) -> Int64 {
        let bytes = Array(s.utf8)
        let len = bytes.count
        var value: Int64 = 0
        for b in bytes {
            value = value * 26 + Int64(b - 97) // 'a' = 97
        }
        // 길이 < len 인 것들 개수(prefix[len-1]) + 같은 길이 내 인덱스(value) + 1
        return prefix[len - 1] + value + 1
    }

    // 정렬된 ban rank 배열 만들기
    var banRanks = [Int64]()
    banRanks.reserveCapacity(bans.count)
    for s in bans {
        banRanks.append(toRank(s))
    }
    banRanks.sort()

    // upperBound: banRanks에서 <= x 개수
    func upperBound(_ x: Int64) -> Int64 {
        var lo = 0
        var hi = banRanks.count
        while lo < hi {
            let mid = (lo + hi) >> 1
            if banRanks[mid] <= x {
                lo = mid + 1
            } else {
                hi = mid
            }
        }
        return Int64(lo)
    }

    // 이분 탐색: 최소 r such that (r - removed(<=r)) >= n
    var lo: Int64 = 1
    var hi: Int64 = total
    while lo < hi {
        let mid = (lo + hi) >> 1
        let removed = upperBound(mid)
        let kept = mid - removed
        if kept >= n {
            hi = mid
        } else {
            lo = mid + 1
        }
    }
    let r = lo

    // rank r -> 문자열로 변환
    var len = 1
    while len <= MAXL && r > prefix[len] { len += 1 }

    // 같은 길이 블록 내 0-based 값
    var offset = r - prefix[len - 1] - 1

    var out = Array(repeating: UInt8(97), count: len) // 'a'
    for i in stride(from: len - 1, through: 0, by: -1) {
        let digit = Int(offset % 26)
        offset /= 26
        out[i] = UInt8(97 + digit)
    }

    return String(bytes: out, encoding: .utf8) ?? ""
}