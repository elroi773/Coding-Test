import Foundation

func solution(_ a: [Int], _ b: [Int]) -> Int {
    let sortedA = a.sorted()
    let sortedB = b.sorted()
    
    var i = 0   // A 포인터
    var j = 0   // B 포인터
    var score = 0
    
    // B의 숫자로 A를 이길 수 있으면 매칭(승점 +1)
    while i < sortedA.count && j < sortedB.count {
        if sortedB[j] > sortedA[i] {
            score += 1
            i += 1
            j += 1
        } else {
            // 이 B는 현재 A도 못 이기므로 더 큰 A도 못 이김 -> 버림
            j += 1
        }
    }
    
    return score
}