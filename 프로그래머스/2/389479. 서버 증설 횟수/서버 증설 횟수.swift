import Foundation

func solution(_ players: [Int], _ m: Int, _ k: Int) -> Int {
    var added = Array(repeating: 0, count: 24)  // i시에 새로 증설한 서버 수
    var active = 0                               // 현재 운영 중인 증설 서버 수
    var answer = 0

    for i in 0..<24 {
        // k시간이 지나 만료되는 서버 반납
        if i >= k {
            active -= added[i - k]
        }

        let required = players[i] / m  // i시간대에 필요한 증설 서버 수

        if active < required {
            let need = required - active
            added[i] = need
            active += need
            answer += need
        }
    }

    return answer
}