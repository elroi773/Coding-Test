import Foundation

func solution(_ plans: [[String]]) -> [String] {
    func toMin(_ s: String) -> Int {
        let parts = s.split(separator: ":")
        let h = Int(parts[0])!
        let m = Int(parts[1])!
        return h * 60 + m
    }

    struct Plan {
        let name: String
        let start: Int
        let play: Int
    }

    var arr: [Plan] = plans.map { Plan(name: $0[0], start: toMin($0[1]), play: Int($0[2])!) }
    arr.sort { $0.start < $1.start }

    var answer: [String] = []
    var stack: [(name: String, remain: Int)] = []

    for i in 0..<arr.count {
        let cur = arr[i]
        let nextStart = (i + 1 < arr.count) ? arr[i + 1].start : Int.max

        var time = cur.play
        var available = nextStart - cur.start

        // 현재 과제를 available 내에서 처리
        if time <= available {
            // 현재 과제 완료
            available -= time
            answer.append(cur.name)

            // 남은 시간으로 멈춘 과제들(LIFO) 처리
            while available > 0, let last = stack.last {
                stack.removeLast()
                if last.remain <= available {
                    available -= last.remain
                    answer.append(last.name)
                } else {
                    stack.append((last.name, last.remain - available))
                    available = 0
                }
            }
        } else {
            // 다 못 끝내면 멈추고 스택에 저장
            stack.append((cur.name, time - available))
        }
    }

    // 마지막 과제 이후엔 스택을 전부 LIFO로 마무리
    while let last = stack.popLast() {
        answer.append(last.name)
    }

    return answer
}