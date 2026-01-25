import Foundation

func solution(_ s: [String]) -> [String] {
    var answer: [String] = []
    answer.reserveCapacity(s.count)

    let one: UInt8 = 49  // '1'
    let zero: UInt8 = 48 // '0'

    for str in s {
        var stack: [UInt8] = []
        stack.reserveCapacity(str.utf8.count)

        var cnt110 = 0

        // 1) "110"을 스택으로 모두 제거하며 개수 카운트
        for b in str.utf8 {
            stack.append(b)
            if stack.count >= 3 {
                let n = stack.count
                if stack[n - 3] == one && stack[n - 2] == one && stack[n - 1] == zero {
                    stack.removeLast(3)
                    cnt110 += 1
                }
            }
        }

        // 2) 삽입 위치: 남은 문자열에서 마지막 '0' 뒤 (없으면 0)
        var insertIdx = 0
        if !stack.isEmpty {
            for i in stride(from: stack.count - 1, through: 0, by: -1) {
                if stack[i] == zero {
                    insertIdx = i + 1
                    break
                }
            }
        }

        // 3) 결과 조립: prefix + ("110"*cnt) + suffix
        var res: [UInt8] = []
        res.reserveCapacity(stack.count + cnt110 * 3)

        if insertIdx > 0 {
            res.append(contentsOf: stack[0..<insertIdx])
        }

        if cnt110 > 0 {
            for _ in 0..<cnt110 {
                res.append(one); res.append(one); res.append(zero)
            }
        }

        if insertIdx < stack.count {
            res.append(contentsOf: stack[insertIdx..<stack.count])
        }

        answer.append(String(decoding: res, as: UTF8.self))
    }

    return answer
}