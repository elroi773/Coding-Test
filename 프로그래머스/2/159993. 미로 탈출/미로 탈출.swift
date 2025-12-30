import Foundation

func solution(_ maps: [String]) -> Int {
    let n = maps.count
    let m = maps[0].count
    let g = maps.map { Array($0) }

    var s = (0, 0), l = (0, 0), e = (0, 0)
    for i in 0..<n {
        for j in 0..<m {
            switch g[i][j] {
            case "S": s = (i, j)
            case "L": l = (i, j)
            case "E": e = (i, j)
            default: break
            }
        }
    }

    let dirs = [(1,0), (-1,0), (0,1), (0,-1)]

    func bfs(_ start: (Int, Int)) -> [[Int]] {
        var dist = Array(repeating: Array(repeating: -1, count: m), count: n)
        var qx: [Int] = [start.0]
        var qy: [Int] = [start.1]
        dist[start.0][start.1] = 0
        var head = 0

        while head < qx.count {
            let x = qx[head]
            let y = qy[head]
            head += 1

            for (dx, dy) in dirs {
                let nx = x + dx
                let ny = y + dy
                if nx < 0 || nx >= n || ny < 0 || ny >= m { continue }
                if dist[nx][ny] != -1 { continue }
                if g[nx][ny] == "X" { continue }
                dist[nx][ny] = dist[x][y] + 1
                qx.append(nx); qy.append(ny)
            }
        }
        return dist
    }

    let distFromS = bfs(s)
    let distFromL = bfs(l)

    let d1 = distFromS[l.0][l.1]
    if d1 == -1 { return -1 }
    let d2 = distFromL[e.0][e.1]
    if d2 == -1 { return -1 }

    return d1 + d2
}