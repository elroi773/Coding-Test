import Foundation

func solution(_ game_board: [[Int]], _ table: [[Int]]) -> Int {
    let n = game_board.count
    let dr = [1, -1, 0, 0]
    let dc = [0, 0, 1, -1]

    func bfs(_ grid: [[Int]], _ target: Int) -> [[(Int, Int)]] {
        var visited = Array(repeating: Array(repeating: false, count: n), count: n)
        var comps: [[(Int, Int)]] = []

        for r in 0..<n {
            for c in 0..<n {
                if visited[r][c] || grid[r][c] != target { continue }

                visited[r][c] = true
                var q: [(Int, Int)] = [(r, c)]
                var head = 0
                var pts: [(Int, Int)] = []

                while head < q.count {
                    let (cr, cc) = q[head]
                    head += 1
                    pts.append((cr, cc))

                    for k in 0..<4 {
                        let nr = cr + dr[k], nc = cc + dc[k]
                        if nr < 0 || nr >= n || nc < 0 || nc >= n { continue }
                        if visited[nr][nc] || grid[nr][nc] != target { continue }
                        visited[nr][nc] = true
                        q.append((nr, nc))
                    }
                }
                comps.append(pts)
            }
        }
        return comps
    }

    func normalize(_ pts: [(Int, Int)]) -> [(Int, Int)] {
        let minR = pts.map { $0.0 }.min() ?? 0
        let minC = pts.map { $0.1 }.min() ?? 0
        return pts.map { ($0.0 - minR, $0.1 - minC) }
            .sorted { $0.0 == $1.0 ? $0.1 < $1.1 : $0.0 < $1.0 }
    }

    // (r,c) -> (c, -r)
    func rotate90(_ pts: [(Int, Int)]) -> [(Int, Int)] {
        return pts.map { ($0.1, -$0.0) }
    }

    func keyString(_ pts: [(Int, Int)]) -> String {
        pts.map { "\($0.0),\($0.1)" }.joined(separator: ";")
    }

    // ✅ 매 회전마다 normalize 한 결과를 기준으로 회전해야 함
    func canonicalKey(from raw: [(Int, Int)]) -> String {
        var pts = raw
        var keys: [String] = []
        for _ in 0..<4 {
            let norm = normalize(pts)
            keys.append(keyString(norm))
            pts = rotate90(norm)   // <-- 여기 핵심 수정
        }
        return keys.min() ?? ""
    }

    // blocks (table의 1 덩어리)
    let blocks = bfs(table, 1)
    var blockCount: [String: Int] = [:]
    for b in blocks {
        let k = canonicalKey(from: b)
        blockCount[k, default: 0] += 1
    }

    // holes (game_board의 0 덩어리)
    let holes = bfs(game_board, 0)
    var filled = 0

    for h in holes {
        let hk = canonicalKey(from: h)
        if let cnt = blockCount[hk], cnt > 0 {
            filled += h.count
            blockCount[hk] = cnt - 1
        }
    }

    return filled
}