import Foundation

func solution(_ topping: [Int]) -> Int {
    // topping 값 범위: 1..10000
    var rightCount = Array(repeating: 0, count: 10001)
    var leftCount = Array(repeating: 0, count: 10001)

    var rightKinds = 0
    var leftKinds = 0

    // 처음엔 전부 오른쪽에 있다고 가정
    for t in topping {
        if rightCount[t] == 0 { rightKinds += 1 }
        rightCount[t] += 1
    }

    var answer = 0

    // i에서 자른다는 건 topping[i]까지 왼쪽, i+1부터 오른쪽
    // 따라서 마지막 토핑은 왼쪽으로 옮길 필요가 없음 (n-2까지만 검사)
    for i in 0..<(topping.count - 1) {
        let t = topping[i]

        // 오른쪽 -> 왼쪽으로 t 하나 이동
        rightCount[t] -= 1
        if rightCount[t] == 0 { rightKinds -= 1 }

        if leftCount[t] == 0 { leftKinds += 1 }
        leftCount[t] += 1

        if leftKinds == rightKinds { answer += 1 }
    }

    return answer
}