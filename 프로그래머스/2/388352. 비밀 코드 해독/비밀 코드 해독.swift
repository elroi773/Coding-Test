import Foundation

func solution(_ n: Int, _ q: [[Int]], _ ans: [Int]) -> Int {
    let m = q.count

    // query를 비트마스크로 변환 (1~n -> bit 0~n-1)
    var qMask = [Int64](repeating: 0, count: m)
    for i in 0..<m {
        var mask: Int64 = 0
        for v in q[i] {
            mask |= (Int64(1) << Int64(v - 1))
        }
        qMask[i] = mask
    }

    var result = 0

    if n < 5 { return 0 } // (문제 조건상 n>=10이라 사실상 불필요)

    // 모든 5개 조합 생성
    for a in 1...(n - 4) {
        for b in (a + 1)...(n - 3) {
            for c in (b + 1)...(n - 2) {
                for d in (c + 1)...(n - 1) {
                    for e in (d + 1)...n {
                        var cand: Int64 = 0
                        cand |= (Int64(1) << Int64(a - 1))
                        cand |= (Int64(1) << Int64(b - 1))
                        cand |= (Int64(1) << Int64(c - 1))
                        cand |= (Int64(1) << Int64(d - 1))
                        cand |= (Int64(1) << Int64(e - 1))

                        var ok = true
                        for i in 0..<m {
                            let match = (cand & qMask[i]).nonzeroBitCount
                            if match != ans[i] {
                                ok = false
                                break
                            }
                        }
                        if ok { result += 1 }
                    }
                }
            }
        }
    }

    return result
}