import Foundation

func solution(_ picks: [Int], _ minerals: [String]) -> Int {
    let totalPicks = picks.reduce(0, +)
    let maxMine = min(minerals.count, totalPicks * 5)
    if maxMine == 0 { return 0 }

    // 5개씩 블록으로 쪼개서 (dia, iron, stone) 개수로 저장
    var blocks: [(d: Int, i: Int, s: Int)] = []
    blocks.reserveCapacity((maxMine + 4) / 5)

    var idx = 0
    while idx < maxMine {
        var d = 0, i = 0, s = 0
        for j in 0..<5 {
            if idx + j >= maxMine { break }
            switch minerals[idx + j] {
            case "diamond": d += 1
            case "iron": i += 1
            default: s += 1
            }
        }
        blocks.append((d, i, s))
        idx += 5
    }

    // "돌 곡괭이로 캤을 때" 피로도가 큰 블록부터 처리 (가장 나쁜 블록 우선)
    // 돌 기준 피로도 = 25*d + 5*i + 1*s
    blocks.sort {
        let a = 25*$0.d + 5*$0.i + $0.s
        let b = 25*$1.d + 5*$1.i + $1.s
        return a > b
    }

    var dia = picks[0], iron = picks[1], stone = picks[2]
    var answer = 0

    for b in blocks {
        if dia > 0 {
            // 다이아 곡괭이: 모두 1
            answer += (b.d + b.i + b.s)
            dia -= 1
        } else if iron > 0 {
            // 철 곡괭이: dia=5, 나머지=1
            answer += 5*b.d + (b.i + b.s)
            iron -= 1
        } else if stone > 0 {
            // 돌 곡괭이: dia=25, iron=5, stone=1
            answer += 25*b.d + 5*b.i + b.s
            stone -= 1
        } else {
            break
        }
    }

    return answer
}