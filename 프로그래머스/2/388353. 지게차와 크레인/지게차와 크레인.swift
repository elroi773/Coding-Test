import Foundation

func solution(_ storage: [String], _ requests: [String]) -> Int {
    let n = storage.count
    let m = storage[0].count
    let dot: UInt8 = 46 // '.'

    // 패딩 포함 그리드 (0..n+1, 0..m+1)
    var grid = Array(repeating: Array(repeating: dot, count: m + 2), count: n + 2)
    for i in 0..<n {
        let row = Array(storage[i].utf8)
        for j in 0..<m {
            grid[i + 1][j + 1] = row[j]
        }
    }

    let dirs = [(1,0), (-1,0), (0,1), (0,-1)]

    for req in requests {
        let r = Array(req.utf8)
        let target = r[0]

        if req.count == 2 {
            // 크레인: 해당 종류 전부 제거
            for i in 1...n {
                for j in 1...m {
                    if grid[i][j] == target {
                        grid[i][j] = dot
                    }
                }
            }
        } else {
            // 지게차: 바깥과 연결된 빈 칸(패딩 포함) 영역을 BFS로 계산
            var visited = Array(repeating: Array(repeating: false, count: m + 2), count: n + 2)
            var qx: [Int] = [0]
            var qy: [Int] = [0]
            visited[0][0] = true
            var head = 0

            while head < qx.count {
                let x = qx[head]
                let y = qy[head]
                head += 1

                for (dx, dy) in dirs {
                    let nx = x + dx
                    let ny = y + dy
                    if nx < 0 || nx >= n + 2 || ny < 0 || ny >= m + 2 { continue }
                    if visited[nx][ny] { continue }
                    if grid[nx][ny] != dot { continue }   // 빈 칸만 이동
                    visited[nx][ny] = true
                    qx.append(nx)
                    qy.append(ny)
                }
            }

            // target 컨테이너 중 "바깥 연결 빈 칸"에 인접한 것만 제거 (요청 순간 기준)
            var toRemove: [(Int, Int)] = []
            for i in 1...n {
                for j in 1...m {
                    if grid[i][j] != target { continue }
                    var accessible = false
                    for (dx, dy) in dirs {
                        let ni = i + dx
                        let nj = j + dy
                        if grid[ni][nj] == dot && visited[ni][nj] {
                            accessible = true
                            break
                        }
                    }
                    if accessible {
                        toRemove.append((i, j))
                    }
                }
            }

            for (i, j) in toRemove {
                grid[i][j] = dot
            }
        }
    }

    // 남은 컨테이너 개수
    var remain = 0
    for i in 1...n {
        for j in 1...m {
            if grid[i][j] != dot { remain += 1 }
        }
    }
    return remain
}