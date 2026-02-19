import Foundation

func solution(_ n: Int, _ costs: [[Int]]) -> Int {
    // Kruskal + Union-Find(DSU)

    var parent = Array(0..<n)
    var rank = Array(repeating: 0, count: n)

    func find(_ x: Int) -> Int {
        if parent[x] == x { return x }
        parent[x] = find(parent[x])   // path compression
        return parent[x]
    }

    func union(_ a: Int, _ b: Int) -> Bool {
        var ra = find(a)
        var rb = find(b)
        if ra == rb { return false }

        if rank[ra] < rank[rb] { swap(&ra, &rb) }
        parent[rb] = ra
        if rank[ra] == rank[rb] { rank[ra] += 1 }
        return true
    }

    let sortedCosts = costs.sorted { $0[2] < $1[2] }

    var answer = 0
    var picked = 0

    for e in sortedCosts {
        let u = e[0], v = e[1], w = e[2]
        if union(u, v) {
            answer += w
            picked += 1
            if picked == n - 1 { break }
        }
    }

    return answer
}