import Foundation

func solution(_ n: Int, _ edge: [[Int]]) -> Int {
    // 1) 인접 리스트 구성
    var graph = Array(repeating: [Int](), count: n + 1)
    for e in edge {
        let a = e[0], b = e[1]
        graph[a].append(b)
        graph[b].append(a)
    }

    // 2) 거리 배열 (-1: 미방문)
    var dist = Array(repeating: -1, count: n + 1)
    dist[1] = 0

    // 3) BFS 큐 (배열 + head 인덱스)
    var queue = [Int]()
    queue.reserveCapacity(n)
    queue.append(1)
    var head = 0

    while head < queue.count {
        let cur = queue[head]
        head += 1

        for next in graph[cur] {
            if dist[next] == -1 {
                dist[next] = dist[cur] + 1
                queue.append(next)
            }
        }
    }

    // 4) 최대 거리와 그 개수 구하기
    var maxDist = 0
    for i in 1...n {
        if dist[i] > maxDist { maxDist = dist[i] }
    }

    var count = 0
    for i in 1...n {
        if dist[i] == maxDist { count += 1 }
    }

    return count
}