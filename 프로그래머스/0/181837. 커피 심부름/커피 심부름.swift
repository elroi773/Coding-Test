import Foundation

func solution(_ order: [String]) -> Int {
    var answer = 0
    for menu in order {
        if menu.contains("cafelatte") {
            answer += 5000
        } else {
            answer += 4500
        }
    }
    return answer
}
