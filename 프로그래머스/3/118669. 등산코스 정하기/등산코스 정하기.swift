import Foundation

struct MinHeap<T> {
    private var heap: [T] = []
    private let areSorted: (T, T) -> Bool

    init(sort: @escaping (T, T) -> Bool) {
        self.areSorted = sort
    }

    var isEmpty: Bool { heap.isEmpty }

    mutating func push(_ x: T) {
        heap.append(x)
        siftUp(from: heap.count - 1)
    }

    mutating func pop() -> T? {
        guard !heap.isEmpty else { return nil }
        if heap.count == 1 { return heap.removeLast() }
        heap.swapAt(0, heap.count - 1)
        let value = heap.removeLast()
        siftDown(from: 0)
        return value
    }

    private mutating func siftUp(from index: Int) {
        var child = index
        var parent = (child - 1) / 2
        while child > 0 && areSorted(heap[child], heap[parent]) {
            heap.swapAt(child, parent)
            child = parent
            parent = (child - 1) / 2
        }
    }

    private mutating func siftDown(from index: Int) {
        var parent = index
        while true {
            let left = parent * 2 + 1
            let right = left + 1
            var candidate = parent

            if left < heap.count && areSorted(heap[left], heap[candidate]) {
                candidate = left
            }
            if right < heap.count && areSorted(heap[right], heap[candidate]) {
                candidate = right
            }
            if candidate == parent { return }
            heap.swapAt(parent, candidate)
            parent = candidate
        }
    }
}

struct Edge {
    let to: Int
    let w: Int
}

struct State {
    let intensity: Int
    let node: Int
}

func solution(_ n: Int, _ paths: [[Int]], _ gates: [Int], _ summits: [Int]) -> [Int] {
    var graph = Array(repeating: [Edge](), count: n + 1)
    for p in paths {
        let a = p[0], b = p[1], w = p[2]
        graph[a].append(Edge(to: b, w: w))
        graph[b].append(Edge(to: a, w: w))
    }

    var isGate = Array(repeating: false, count: n + 1)
    for g in gates { isGate[g] = true }

    var isSummit = Array(repeating: false, count: n + 1)
    for s in summits { isSummit[s] = true }

    let INF = Int.max
    var dist = Array(repeating: INF, count: n + 1)

    var pq = MinHeap<State>(sort: { $0.intensity < $1.intensity })

    for g in gates {
        dist[g] = 0
        pq.push(State(intensity: 0, node: g))
    }

    var bestIntensity = INF

    while let cur = pq.pop() {
        let u = cur.node
        let inten = cur.intensity

        if inten > dist[u] { continue }
        if inten > bestIntensity { break } // 더 나은 summit 불가능

        if isSummit[u] {
            // summit은 도착만 (확장 X)
            if inten < bestIntensity { bestIntensity = inten }
            continue
        }

        for e in graph[u] {
            let v = e.to
            if isGate[v] { continue }         // 다른 출입구 방문 금지
            if isSummit[u] { continue }       // (위에서 걸리지만 안전장치)
            let nextIntensity = max(inten, e.w)
            if nextIntensity < dist[v] {
                dist[v] = nextIntensity
                pq.push(State(intensity: nextIntensity, node: v))
            }
        }
    }

    let sortedSummits = summits.sorted()
    var answerSummit = sortedSummits[0]
    var answerIntensity = dist[answerSummit]

    for s in sortedSummits {
        let v = dist[s]
        if v < answerIntensity {
            answerIntensity = v
            answerSummit = s
        }
    }

    return [answerSummit, answerIntensity]
}