import Foundation

func solution(_ n: Int, _ lighthouse: [[Int]]) -> Int {
    // 인접 리스트
    var g = Array(repeating: [Int](), count: n + 1)
    g.reserveCapacity(n + 1)

    for e in lighthouse {
        let a = e[0], b = e[1]
        g[a].append(b)
        g[b].append(a)
    }

    // parent + DFS 방문 순서
    var parent = Array(repeating: 0, count: n + 1)
    var order = [Int]()
    order.reserveCapacity(n)

    var stack = [Int]()
    stack.reserveCapacity(n)

    stack.append(1)
    parent[1] = -1

    while let u = stack.popLast() {
        order.append(u)
        for v in g[u] {
            if v == parent[u] { continue }
            if parent[v] != 0 { continue } // 이미 방문
            parent[v] = u
            stack.append(v)
        }
    }

    // dp0[u]: u 끔(선택X), dp1[u]: u 켬(선택O)
    var dp0 = Array(repeating: 0, count: n + 1)
    var dp1 = Array(repeating: 0, count: n + 1)

    // 후위 순회 효과: order 역순으로 DP
    for u in order.reversed() {
        var off = 0   // u를 끄면 자식은 반드시 켜야 간선 커버
        var on = 1    // u를 켜면 1개 추가, 자식은 min 선택

        for v in g[u] {
            if v == parent[u] { continue }
            off += dp1[v]
            on += min(dp0[v], dp1[v])
        }

        dp0[u] = off
        dp1[u] = on
    }

    return min(dp0[1], dp1[1])
}