import Foundation

func solution(_ board: [[Int]]) -> Int {
    let n = board.count
    let INF = Int.max / 4

    // 방향: 0=상, 1=하, 2=좌, 3=우
    let dx = [-1, 1, 0, 0]
    let dy = [0, 0, -1, 1]

    // dist[x][y][dir] = (x,y)에 dir 방향으로 들어온 최소 비용
    var dist = Array(
        repeating: Array(
            repeating: Array(repeating: INF, count: 4),
            count: n
        ),
        count: n
    )

    struct Node {
        let cost: Int
        let x: Int
        let y: Int
        let dir: Int
    }

    // Min-Heap (cost 기준)
    struct MinHeap {
        var heap: [Node] = []

        mutating func push(_ x: Node) {
            heap.append(x)
            siftUp(from: heap.count - 1)
        }

        mutating func pop() -> Node? {
            guard !heap.isEmpty else { return nil }
            if heap.count == 1 { return heap.removeLast() }
            let top = heap[0]
            heap[0] = heap.removeLast()
            siftDown(from: 0)
            return top
        }

        var isEmpty: Bool { heap.isEmpty }

        mutating func siftUp(from index: Int) {
            var child = index
            while child > 0 {
                let parent = (child - 1) / 2
                if heap[child].cost < heap[parent].cost {
                    heap.swapAt(child, parent)
                    child = parent
                } else {
                    break
                }
            }
        }

        mutating func siftDown(from index: Int) {
            var parent = index
            while true {
                let left = parent * 2 + 1
                let right = left + 1
                var candidate = parent

                if left < heap.count && heap[left].cost < heap[candidate].cost {
                    candidate = left
                }
                if right < heap.count && heap[right].cost < heap[candidate].cost {
                    candidate = right
                }
                if candidate == parent { break }
                heap.swapAt(parent, candidate)
                parent = candidate
            }
        }
    }

    // 시작이 곧 도착인 경우(문제 조건상 n>=3이지만 안전하게)
    if n == 1 { return 0 }

    var pq = MinHeap()

    // 시작점 (0,0)에서는 "첫 이동"은 코너 비용이 없음 => 무조건 +100
    for d in 0..<4 {
        let nx = 0 + dx[d]
        let ny = 0 + dy[d]
        if nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0 {
            dist[nx][ny][d] = 100
            pq.push(Node(cost: 100, x: nx, y: ny, dir: d))
        }
    }

    while let cur = pq.pop() {
        let cost = cur.cost
        let x = cur.x
        let y = cur.y
        let dir = cur.dir

        if cost > dist[x][y][dir] { continue }

        for nd in 0..<4 {
            let nx = x + dx[nd]
            let ny = y + dy[nd]
            if nx < 0 || nx >= n || ny < 0 || ny >= n { continue }
            if board[nx][ny] == 1 { continue }

            let add = (nd == dir) ? 100 : 600
            let newCost = cost + add

            if newCost < dist[nx][ny][nd] {
                dist[nx][ny][nd] = newCost
                pq.push(Node(cost: newCost, x: nx, y: ny, dir: nd))
            }
        }
    }

    return dist[n - 1][n - 1].min()!
}