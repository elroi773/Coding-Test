import Foundation

func solution(_ board: [[Int]], _ skill: [[Int]]) -> Int {
    let n = board.count
    let m = board[0].count

    // diff는 (n+1) x (m+1) 크기로 잡아 r2+1, c2+1 업데이트를 안전하게 처리
    let nn = n + 1
    let mm = m + 1
    var diff = Array(repeating: 0, count: nn * mm)

    @inline(__always)
    func idx(_ r: Int, _ c: Int) -> Int {
        return r * mm + c
    }

    // 스킬들을 diff에 사각형 업데이트로 기록
    for s in skill {
        let type = s[0]
        let r1 = s[1], c1 = s[2], r2 = s[3], c2 = s[4]
        let degree = s[5]

        let delta = (type == 1) ? -degree : degree

        diff[idx(r1, c1)] += delta
        diff[idx(r1, c2 + 1)] -= delta
        diff[idx(r2 + 1, c1)] -= delta
        diff[idx(r2 + 1, c2 + 1)] += delta
    }

    // 가로 누적합
    for r in 0...n {
        var run = 0
        for c in 0...m {
            run += diff[idx(r, c)]
            diff[idx(r, c)] = run
        }
    }

    // 세로 누적합
    for c in 0...m {
        var run = 0
        for r in 0...n {
            run += diff[idx(r, c)]
            diff[idx(r, c)] = run
        }
    }

    // 최종 내구도 계산 후 1 이상인 건물 카운트
    var alive = 0
    for r in 0..<n {
        for c in 0..<m {
            if board[r][c] + diff[idx(r, c)] > 0 {
                alive += 1
            }
        }
    }

    return alive
}