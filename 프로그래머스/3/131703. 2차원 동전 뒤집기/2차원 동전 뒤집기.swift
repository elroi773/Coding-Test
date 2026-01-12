import Foundation

func solution(_ beginning: [[Int]], _ target: [[Int]]) -> Int {
    let R = beginning.count
    let C = beginning[0].count

    // diff = beginning XOR target
    var diff = Array(repeating: Array(repeating: 0, count: C), count: R)
    for i in 0..<R {
        for j in 0..<C {
            diff[i][j] = beginning[i][j] ^ target[i][j]
        }
    }

    var best = Int.max
    let totalMasks = 1 << R

    for mask in 0..<totalMasks {
        // rowFlip(i) = 1 if i-th bit is set
        @inline(__always) func rowFlip(_ i: Int) -> Int {
            return ((mask >> i) & 1)
        }

        // 열 뒤집기 여부는 0행을 0으로 만들도록 자동 결정
        var colFlip = Array(repeating: 0, count: C)
        let rf0 = rowFlip(0)
        for j in 0..<C {
            colFlip[j] = diff[0][j] ^ rf0
        }

        // 전체 검증: diff[i][j] ^ rowFlip(i) ^ colFlip(j) == 0 이어야 함
        var ok = true
        for i in 0..<R {
            let rfi = rowFlip(i)
            for j in 0..<C {
                if (diff[i][j] ^ rfi ^ colFlip[j]) != 0 {
                    ok = false
                    break
                }
            }
            if !ok { break }
        }

        if ok {
            let rowCount = mask.nonzeroBitCount
            var colCount = 0
            for j in 0..<C { colCount += colFlip[j] }
            best = min(best, rowCount + colCount)
        }
    }

    return best == Int.max ? -1 : best
}