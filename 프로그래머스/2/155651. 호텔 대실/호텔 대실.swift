import Foundation

func solution(_ book_time: [[String]]) -> Int {
    func toMin(_ t: String) -> Int {
        let parts = t.split(separator: ":")
        return Int(parts[0])! * 60 + Int(parts[1])!
    }

    // (start, end+10)로 변환 후 start 오름차순 정렬
    var books: [(Int, Int)] = book_time.map {
        let s = toMin($0[0])
        let e = toMin($0[1]) + 10
        return (s, e)
    }
    books.sort { $0.0 < $1.0 }

    // 최소 힙 구현 (끝나는 시간들)
    struct MinHeap {
        var a: [Int] = []
        var isEmpty: Bool { a.isEmpty }
        var peek: Int { a[0] }

        mutating func push(_ x: Int) {
            a.append(x)
            var i = a.count - 1
            while i > 0 {
                let p = (i - 1) / 2
                if a[p] <= a[i] { break }
                a.swapAt(p, i)
                i = p
            }
        }

        mutating func pop() -> Int {
            let res = a[0]
            let last = a.removeLast()
            if !a.isEmpty {
                a[0] = last
                var i = 0
                while true {
                    let l = i * 2 + 1
                    let r = l + 1
                    var smallest = i
                    if l < a.count && a[l] < a[smallest] { smallest = l }
                    if r < a.count && a[r] < a[smallest] { smallest = r }
                    if smallest == i { break }
                    a.swapAt(i, smallest)
                    i = smallest
                }
            }
            return res
        }
    }

    var heap = MinHeap()
    var answer = 0

    for (start, end) in books {
        // 지금 시작(start) 전에 끝난 방들은 재사용 가능
        while !heap.isEmpty && heap.peek <= start {
            _ = heap.pop()
        }
        heap.push(end)
        answer = max(answer, heap.a.count)
    }

    return answer
}