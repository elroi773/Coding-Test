import Foundation

// (value, id)를 저장해서 lazy deletion(지연 삭제) 처리
struct Heap {
    var data: [(Int, Int)] = []
    let isHigherPriority: ((Int, Int), (Int, Int)) -> Bool

    init(_ cmp: @escaping ((Int, Int), (Int, Int)) -> Bool) {
        self.isHigherPriority = cmp
    }

    var isEmpty: Bool { data.isEmpty }
    func peek() -> (Int, Int)? { data.first }

    mutating func push(_ x: (Int, Int)) {
        data.append(x)
        siftUp(from: data.count - 1)
    }

    mutating func pop() -> (Int, Int)? {
        guard !data.isEmpty else { return nil }
        if data.count == 1 { return data.removeLast() }
        let top = data[0]
        data[0] = data.removeLast()
        siftDown(from: 0)
        return top
    }

    private mutating func siftUp(from index: Int) {
        var child = index
        while child > 0 {
            let parent = (child - 1) / 2
            if isHigherPriority(data[child], data[parent]) {
                data.swapAt(child, parent)
                child = parent
            } else {
                break
            }
        }
    }

    private mutating func siftDown(from index: Int) {
        var parent = index
        while true {
            let left = parent * 2 + 1
            let right = left + 1
            var best = parent

            if left < data.count, isHigherPriority(data[left], data[best]) {
                best = left
            }
            if right < data.count, isHigherPriority(data[right], data[best]) {
                best = right
            }
            if best == parent { break }
            data.swapAt(parent, best)
            parent = best
        }
    }
}

func solution(_ operations: [String]) -> [Int] {
    // minHeap: 작은 값 우선, maxHeap: 큰 값 우선
    var minHeap = Heap { a, b in a.0 < b.0 }
    var maxHeap = Heap { a, b in a.0 > b.0 }

    var alive: [Bool] = []      // id가 아직 유효한지
    alive.reserveCapacity(operations.count)

    var nextId = 0
    var validCount = 0

    // 힙의 top이 죽은(id가 이미 삭제된) 값이면 제거
    func cleanTop(_ heap: inout Heap) {
        while let top = heap.peek(), top.1 < alive.count, alive[top.1] == false {
            _ = heap.pop()
        }
    }

    for op in operations {
        // 빠르고 간단하게 split 사용 (명령이 "I x" 또는 "D 1/-1" 형태)
        let parts = op.split(separator: " ")
        if parts.isEmpty { continue }

        if parts[0] == "I" {
            let x = Int(parts[1])!
            let id = nextId
            nextId += 1

            alive.append(true)
            minHeap.push((x, id))
            maxHeap.push((x, id))
            validCount += 1
        } else { // "D"
            if validCount == 0 { continue }

            if parts[1] == "1" {
                // delete max
                cleanTop(&maxHeap)
                if let removed = maxHeap.pop() {
                    alive[removed.1] = false
                    validCount -= 1
                }
            } else {
                // delete min
                cleanTop(&minHeap)
                if let removed = minHeap.pop() {
                    alive[removed.1] = false
                    validCount -= 1
                }
            }
        }
    }

    if validCount == 0 { return [0, 0] }

    cleanTop(&minHeap)
    cleanTop(&maxHeap)

    let maxVal = maxHeap.peek()!.0
    let minVal = minHeap.peek()!.0
    return [maxVal, minVal]
}