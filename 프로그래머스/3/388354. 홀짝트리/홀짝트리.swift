import Foundation

func solution(_ nodes: [Int], _ edges: [[Int]]) -> [Int] {
    let n = nodes.count
    if n == 0 { return [0, 0] }

    // value -> index
    var idxOf = Dictionary<Int, Int>(minimumCapacity: n * 2)
    for (i, v) in nodes.enumerated() { idxOf[v] = i }

    // degrees
    var deg = Array(repeating: 0, count: n)

    // DSU
    var parent = Array(0..<n)
    var size = Array(repeating: 1, count: n)

    func find(_ x: Int) -> Int {
        var x = x
        while parent[x] != x {
            parent[x] = parent[parent[x]]
            x = parent[x]
        }
        return x
    }

    func union(_ a: Int, _ b: Int) {
        var ra = find(a)
        var rb = find(b)
        if ra == rb { return }
        if size[ra] < size[rb] { swap(&ra, &rb) }
        parent[rb] = ra
        size[ra] += size[rb]
    }

    // build DSU + degree
    for e in edges {
        let aVal = e[0], bVal = e[1]
        guard let a = idxOf[aVal], let b = idxOf[bVal] else { continue }
        deg[a] += 1
        deg[b] += 1
        union(a, b)
    }

    // per component counts
    var compSize = Array(repeating: 0, count: n)
    var compA = Array(repeating: 0, count: n) // deg%2 == val%2
    var compB = Array(repeating: 0, count: n) // deg%2 != val%2

    for i in 0..<n {
        let r = find(i)
        compSize[r] += 1
        let a = (deg[i] & 1) == (nodes[i] & 1)
        if a { compA[r] += 1 } else { compB[r] += 1 }
    }

    var oddEvenTrees = 0
    var revOddEvenTrees = 0

    for i in 0..<n where parent[i] == i && compSize[i] > 0 {
        if compA[i] == 1 { oddEvenTrees += 1 }
        if compB[i] == 1 { revOddEvenTrees += 1 }
    }

    return [oddEvenTrees, revOddEvenTrees]
}