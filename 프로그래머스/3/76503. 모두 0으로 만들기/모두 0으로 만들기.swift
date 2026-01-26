import Foundation

func solution(_ a: [Int], _ edges: [[Int]]) -> Int64 {
    let n = a.count

    var sum: Int64 = 0
    for x in a { sum += Int64(x) }
    if sum != 0 { return -1 }

    // 인접 리스트 (head / to / next) - 배열 기반
    let m = edges.count
    let E = 2 * m
    var head = Array(repeating: -1, count: n)
    var to = Array(repeating: 0, count: E)
    var next = Array(repeating: 0, count: E)

    var idx = 0
    for e in edges {
        let u = e[0], v = e[1]

        to[idx] = v
        next[idx] = head[u]
        head[u] = idx
        idx += 1

        to[idx] = u
        next[idx] = head[v]
        head[v] = idx
        idx += 1
    }

    // parent + DFS order (stack)
    var parent = Array(repeating: -1, count: n)
    var order = Array(repeating: 0, count: n)
    var ordSz = 0

    var stack = Array(repeating: 0, count: n)
    var top = 0

    stack[top] = 0
    top += 1
    parent[0] = 0

    while top > 0 {
        top -= 1
        let u = stack[top]
        order[ordSz] = u
        ordSz += 1

        var e = head[u]
        while e != -1 {
            let v = to[e]
            if parent[v] == -1 {
                parent[v] = u
                stack[top] = v
                top += 1
            }
            e = next[e]
        }
    }

    // 값은 Int64로 관리
    var val = a.map { Int64($0) }

    // 후위 처리(역순), 루트(0) 제외
    var ans: Int64 = 0
    if ordSz > 1 {
        for i in stride(from: ordSz - 1, through: 1, by: -1) {
            let u = order[i]
            let x = val[u]
            ans += x >= 0 ? x : -x
            let p = parent[u]
            val[p] += x
            val[u] = 0
        }
    }

    return ans
}