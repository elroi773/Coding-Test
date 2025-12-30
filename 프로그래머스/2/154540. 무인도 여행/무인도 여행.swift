import Foundation

func solution(_ maps: [String]) -> [Int] {
    let n = maps.count
    let m = maps[0].count
    let grid = maps.map { Array($0) }

    var visited = Array(repeating: Array(repeating: false, count: m), count: n)
    let dirs = [(1,0), (-1,0), (0,1), (0,-1)]

    var result: [Int] = []

    for i in 0..<n {
        for j in 0..<m {
            if visited[i][j] { continue }
            if grid[i][j] == "X" { continue }

            // BFS로 한 섬의 합 계산
            var sum = 0
            var qx: [Int] = [i]
            var qy: [Int] = [j]
            visited[i][j] = true
            var head = 0

            while head < qx.count {
                let x = qx[head]
                let y = qy[head]
                head += 1

                // '1'~'9' -> Int
                sum += Int(String(grid[x][y]))!

                for (dx, dy) in dirs {
                    let nx = x + dx
                    let ny = y + dy
                    if nx < 0 || nx >= n || ny < 0 || ny >= m { continue }
                    if visited[nx][ny] { continue }
                    if grid[nx][ny] == "X" { continue }
                    visited[nx][ny] = true
                    qx.append(nx); qy.append(ny)
                }
            }

            result.append(sum)
        }
    }

    if result.isEmpty { return [-1] }
    return result.sorted()
}