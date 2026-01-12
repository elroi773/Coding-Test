import Foundation

func solution(_ n: Int, _ roads: [[Int]], _ sources: [Int], _ destination: Int) -> [Int] {
    // 1) 각 노드의 차수(degree) 먼저 계산해서 adjacency list 용량 reserve (성능 개선)
    var deg = Array(repeating: 0, count: n + 1)
    for e in roads {
        let a = e[0], b = e[1]
        deg[a] += 1
        deg[b] += 1
    }

    // 2) 인접 리스트 구성
    var graph = Array(repeating: [Int](), count: n + 1)
    if n >= 1 {
        for i in 1...n {
            graph[i].reserveCapacity(deg[i])
        }
    }
    for e in roads {
        let a = e[0], b = e[1]
        graph[a].append(b)
        graph[b].append(a)
    }

    // 3) destination에서 BFS로 모든 최단거리 계산
    var dist = Array(repeating: -1, count: n + 1)
    dist[destination] = 0

    var queue: [Int] = []
    queue.reserveCapacity(n)
    queue.append(destination)
    var head = 0

    while head < queue.count {
        let cur = queue[head]
        head += 1

        let nextDist = dist[cur] + 1
        for nxt in graph[cur] {
            if dist[nxt] == -1 {
                dist[nxt] = nextDist
                queue.append(nxt)
            }
        }
    }

    // 4) sources 순서대로 결과 반환
    return sources.map { dist[$0] }
}