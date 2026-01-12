import Foundation

func solution(_ clockHands: [[Int]]) -> Int {
    let n = clockHands.count
    let original = clockHands.flatMap { $0 }          // 1D로 펼침 (n*n)
    var best = Int.max

    @inline(__always)
    func addMod4(_ board: inout [Int], _ idx: Int, _ t: Int) {
        board[idx] = (board[idx] + t) & 3             // mod 4 (0~3)
    }

    @inline(__always)
    func press(_ board: inout [Int], _ r: Int, _ c: Int, _ t: Int) {
        if t == 0 { return }
        let idx = r * n + c
        addMod4(&board, idx, t)                       // self
        if r > 0 { addMod4(&board, (r - 1) * n + c, t) }      // up
        if r < n - 1 { addMod4(&board, (r + 1) * n + c, t) }  // down
        if c > 0 { addMod4(&board, r * n + (c - 1), t) }      // left
        if c < n - 1 { addMod4(&board, r * n + (c + 1), t) }  // right
    }

    // 4^n = 2^(2n)
    let cases = 1 << (2 * n)

    for mask in 0..<cases {
        var board = original
        var cnt = 0
        var tmp = mask

        // 1) 첫 행 조작(각 칸 0~3회) - mask를 2비트씩 사용
        for c in 0..<n {
            let t = tmp & 3
            tmp >>= 2
            if t != 0 {
                cnt += t
                press(&board, 0, c, t)
            }
        }

        // 2) 2행~n행: 윗행을 0으로 만들기 위한 조작은 자동 결정
        if n >= 2 {
            for r in 1..<n {
                for c in 0..<n {
                    let above = board[(r - 1) * n + c]
                    if above != 0 {
                        let t = (4 - above) & 3       // 0~3
                        cnt += t
                        press(&board, r, c, t)
                    }
                }
            }
        }

        // 3) 마지막 행이 모두 0이면 성공
        var ok = true
        for c in 0..<n {
            if board[(n - 1) * n + c] != 0 {
                ok = false
                break
            }
        }

        if ok {
            best = min(best, cnt)
        }
    }

    return best
}