import Foundation

func solution(_ board: [[Int]]) -> Int {
    let n = board.count
    
    // 1-based padding (테두리 1로 막기) -> 범위 체크 쉬움
    var b = Array(repeating: Array(repeating: 1, count: n + 2), count: n + 2)
    for i in 0..<n {
        for j in 0..<n {
            b[i + 1][j + 1] = board[i][j]
        }
    }
    
    // 상태 정규화: (r1,c1) <= (r2,c2) (사전순)
    func norm(_ r1: Int, _ c1: Int, _ r2: Int, _ c2: Int) -> (Int, Int, Int, Int) {
        if r1 < r2 || (r1 == r2 && c1 <= c2) {
            return (r1, c1, r2, c2)
        } else {
            return (r2, c2, r1, c1)
        }
    }
    
    // 방문 체크용 키
    func key(_ s: (Int, Int, Int, Int)) -> String {
        return "\(s.0),\(s.1),\(s.2),\(s.3)"
    }
    
    // 목표 도착 체크: 두 칸 중 하나라도 (n,n)
    func isGoal(_ s: (Int, Int, Int, Int)) -> Bool {
        let (r1, c1, r2, c2) = s
        return (r1 == n && c1 == n) || (r2 == n && c2 == n)
    }
    
    // 시작 상태: (1,1)-(1,2) 가로
    let start = norm(1, 1, 1, 2)
    
    // BFS 큐
    var queue: [((Int, Int, Int, Int), Int)] = []
    queue.append((start, 0))
    var head = 0
    
    var visited = Set<String>()
    visited.insert(key(start))
    
    // 이동 방향
    let dr = [-1, 1, 0, 0]
    let dc = [0, 0, -1, 1]
    
    while head < queue.count {
        let (cur, dist) = queue[head]
        head += 1
        
        if isGoal(cur) { return dist }
        
        let (r1, c1, r2, c2) = cur
        
        // 1) 상하좌우 이동
        for i in 0..<4 {
            let nr1 = r1 + dr[i], nc1 = c1 + dc[i]
            let nr2 = r2 + dr[i], nc2 = c2 + dc[i]
            if b[nr1][nc1] == 0 && b[nr2][nc2] == 0 {
                let nxt = norm(nr1, nc1, nr2, nc2)
                let k = key(nxt)
                if !visited.contains(k) {
                    visited.insert(k)
                    queue.append((nxt, dist + 1))
                }
            }
        }
        
        // 2) 회전
        // 가로 상태: r1 == r2
        if r1 == r2 {
            // 위로 회전 / 아래로 회전 (두 칸 모두 옆에 공간 필요)
            for dir in [-1, 1] { // -1: 위, 1: 아래
                if b[r1 + dir][c1] == 0 && b[r2 + dir][c2] == 0 {
                    // 축: (r1,c1)
                    var nxt1 = norm(r1, c1, r1 + dir, c1)
                    var k1 = key(nxt1)
                    if !visited.contains(k1) {
                        visited.insert(k1)
                        queue.append((nxt1, dist + 1))
                    }
                    // 축: (r2,c2)
                    var nxt2 = norm(r2, c2, r2 + dir, c2)
                    var k2 = key(nxt2)
                    if !visited.contains(k2) {
                        visited.insert(k2)
                        queue.append((nxt2, dist + 1))
                    }
                }
            }
        }
        
        // 세로 상태: c1 == c2
        if c1 == c2 {
            // 왼쪽 회전 / 오른쪽 회전 (두 칸 모두 옆에 공간 필요)
            for dir in [-1, 1] { // -1: 왼쪽, 1: 오른쪽
                if b[r1][c1 + dir] == 0 && b[r2][c2 + dir] == 0 {
                    // 축: (r1,c1)
                    var nxt1 = norm(r1, c1, r1, c1 + dir)
                    var k1 = key(nxt1)
                    if !visited.contains(k1) {
                        visited.insert(k1)
                        queue.append((nxt1, dist + 1))
                    }
                    // 축: (r2,c2)
                    var nxt2 = norm(r2, c2, r2, c2 + dir)
                    var k2 = key(nxt2)
                    if !visited.contains(k2) {
                        visited.insert(k2)
                        queue.append((nxt2, dist + 1))
                    }
                }
            }
        }
    }
    
    return -1 // 문제 조건상 도달 가능하지만 안전장치
}