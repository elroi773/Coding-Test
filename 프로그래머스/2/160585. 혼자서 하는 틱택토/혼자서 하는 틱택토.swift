import Foundation

func solution(_ board: [String]) -> Int {
    let g = board.map { Array($0) }

    var oCnt = 0, xCnt = 0
    for i in 0..<3 {
        for j in 0..<3 {
            if g[i][j] == "O" { oCnt += 1 }
            else if g[i][j] == "X" { xCnt += 1 }
        }
    }

    // 개수 조건
    if !(oCnt == xCnt || oCnt == xCnt + 1) { return 0 }

    func win(_ ch: Character) -> Bool {
        // rows
        for i in 0..<3 {
            if g[i][0] == ch && g[i][1] == ch && g[i][2] == ch { return true }
        }
        // cols
        for j in 0..<3 {
            if g[0][j] == ch && g[1][j] == ch && g[2][j] == ch { return true }
        }
        // diagonals
        if g[0][0] == ch && g[1][1] == ch && g[2][2] == ch { return true }
        if g[0][2] == ch && g[1][1] == ch && g[2][0] == ch { return true }
        return false
    }

    let oWin = win("O")
    let xWin = win("X")

    // 동시에 승리 불가
    if oWin && xWin { return 0 }

    // 승리한 쪽에 따른 개수 조건
    if oWin && oCnt != xCnt + 1 { return 0 }
    if xWin && oCnt != xCnt { return 0 }

    return 1
}