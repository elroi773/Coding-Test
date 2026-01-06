import Foundation

func solution(_ order: [Int]) -> Int {
    let n = order.count
    var stack = [Int]()
    stack.reserveCapacity(n)

    var cur = 1          // 메인 벨트에서 다음으로 꺼낼 상자 번호
    var loaded = 0       // 실은 개수

    for target in order {
        // 1) 보조 벨트(스택) 맨 위가 target이면 바로 싣기
        if let last = stack.last, last == target {
            stack.removeLast()
            loaded += 1
            continue
        }

        // 2) 메인 벨트에서 target이 나올 때까지 스택에 임시 보관
        while cur <= n && cur != target {
            stack.append(cur)
            cur += 1
        }

        // 3) 메인 벨트에서 target을 만났으면 싣기
        if cur == target {
            cur += 1
            loaded += 1
        } else {
            // 메인도 끝났고, 스택 top도 target이 아니면 더 이상 불가
            break
        }
    }

    return loaded
}