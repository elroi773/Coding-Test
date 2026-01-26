import Foundation

func solution(_ board: [[Int]], _ r: Int, _ c: Int) -> Int {
    let N = 4
    let dr = [-1, 1, 0, 0]
    let dc = [0, 0, -1, 1]

    @inline(__always) func idx(_ rr: Int, _ cc: Int) -> Int { rr * 4 + cc }
    @inline(__always) func inRange(_ rr: Int, _ cc: Int) -> Bool { (0..<4).contains(rr) && (0..<4).contains(cc) }

    // 초기 마스크(남아있는 카드 위치) + 카드 값별 위치(각 2개)
    var mask = 0
    var pos = Array(repeating: [Int](), count: 7) // 값 1..6
    for i in 0..<4 {
        for j in 0..<4 {
            let v = board[i][j]
            if v != 0 {
                let k = idx(i, j)
                mask |= (1 << k)
                if v <= 6 { pos[v].append(k) }
            }
        }
    }

    // 실제 존재하는 카드 종류 목록
    var types: [Int] = []
    for v in 1...6 where pos[v].count == 2 { types.append(v) }

    // Ctrl 이동
    func ctrlMove(_ from: Int, _ dir: Int, _ curMask: Int) -> Int {
        var rr = from / 4
        var cc = from % 4
        while true {
            let nr = rr + dr[dir]
            let nc = cc + dc[dir]
            if !inRange(nr, nc) { break }
            rr = nr; cc = nc
            let ni = idx(rr, cc)
            if (curMask & (1 << ni)) != 0 { // 카드 발견
                return ni
            }
        }
        return idx(rr, cc) // 끝까지 가서 마지막 칸
    }

    // 1칸 이동
    func stepMove(_ from: Int, _ dir: Int) -> Int {
        let rr = from / 4
        let cc = from % 4
        let nr = rr + dr[dir]
        let nc = cc + dc[dir]
        if !inRange(nr, nc) { return from }
        return idx(nr, nc)
    }

    // BFS: start -> target 최소 키 입력(방향키/ctrl키)
    func dist(_ start: Int, _ target: Int, _ curMask: Int) -> Int {
        if start == target { return 0 }
        var visited = Array(repeating: -1, count: 16)
        var q = Array(repeating: 0, count: 16)
        var head = 0, tail = 0
        q[tail] = start; tail += 1
        visited[start] = 0

        while head < tail {
            let u = q[head]; head += 1
            let d = visited[u]

            for dir in 0..<4 {
                // 일반 이동
                let v1 = stepMove(u, dir)
                if visited[v1] == -1 {
                    visited[v1] = d + 1
                    if v1 == target { return visited[v1] }
                    q[tail] = v1; tail += 1
                }
                // Ctrl 이동
                let v2 = ctrlMove(u, dir, curMask)
                if visited[v2] == -1 {
                    visited[v2] = d + 1
                    if v2 == target { return visited[v2] }
                    q[tail] = v2; tail += 1
                }
            }
        }
        return visited[target]
    }

    // 메모: key = (mask<<4) | cursor
    var memo: [Int64: Int] = [:]

    func dfs(_ curMask: Int, _ cursor: Int) -> Int {
        if curMask == 0 { return 0 }
        let key = (Int64(curMask) << 4) | Int64(cursor)
        if let v = memo[key] { return v }

        var best = Int.max

        for t in types {
            let p = pos[t]
            let a1 = p[0], a2 = p[1]
            let b1 = (curMask & (1 << a1)) != 0
            let b2 = (curMask & (1 << a2)) != 0
            if !b1 && !b2 { continue } // 이미 제거됨

            let nextMask = curMask & ~(1 << a1) & ~(1 << a2)

            // 순서 1: cursor -> a1(Enter) -> a2(Enter)
            let cost1 = dist(cursor, a1, curMask) + 1 + dist(a1, a2, curMask) + 1
            best = min(best, cost1 + dfs(nextMask, a2))

            // 순서 2: cursor -> a2(Enter) -> a1(Enter)
            let cost2 = dist(cursor, a2, curMask) + 1 + dist(a2, a1, curMask) + 1
            best = min(best, cost2 + dfs(nextMask, a1))
        }

        memo[key] = best
        return best
    }

    return dfs(mask, idx(r, c))
}