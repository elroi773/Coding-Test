import Foundation

func solution(_ board: [String]) -> Int {
    let n = board.count
    let m = board[0].count
    let grid = board.map { Array($0) }

    var start = (0, 0)
    var goal = (0, 0)

    for i in 0..<n {
        for j in 0..<m {
            if grid[i][j] == "R" { start = (i, j) }
            else if grid[i][j] == "G" { goal = (i, j) }
        }
    }

    var dist = Array(repeating: Array(repeating: -1, count: m), count: n)
    var qx: [Int] = []
    var qy: [Int] = []
    var head = 0

    dist[start.0][start.1] = 0
    qx.append(start.0); qy.append(start.1)

    let dirs = [(1,0), (-1,0), (0,1), (0,-1)]

    func slide(_ x: Int, _ y: Int, _ dx: Int, _ dy: Int) -> (Int, Int) {
        var cx = x
        var cy = y
        while true {
            let nx = cx + dx
            let ny = cy + dy
            if nx < 0 || nx >= n || ny < 0 || ny >= m { break }
            if grid[nx][ny] == "D" { break }
            cx = nx
            cy = ny
        }
        return (cx, cy)
    }

    while head < qx.count {
        let x = qx[head]
        let y = qy[head]
        head += 1

        if x == goal.0 && y == goal.1 {
            return dist[x][y]
        }

        for (dx, dy) in dirs {
            let (nx, ny) = slide(x, y, dx, dy)
            if dist[nx][ny] == -1 {
                dist[nx][ny] = dist[x][y] + 1
                qx.append(nx); qy.append(ny)
            }
        }
    }

    return -1
}