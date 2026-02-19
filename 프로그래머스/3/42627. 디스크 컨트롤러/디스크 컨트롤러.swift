import Foundation

// 우선순위: 소요시간 짧은 것 -> 요청시각 빠른 것 -> 작업번호 작은 것
// jobs[i] = [requestTime, duration], i가 작업번호
struct Job {
    let dur: Int
    let req: Int
    let id: Int
}

struct MinHeap {
    private var a: [Job] = []

    var isEmpty: Bool { a.isEmpty }
    func peek() -> Job? { a.first }

    mutating func push(_ x: Job) {
        a.append(x)
        siftUp(a.count - 1)
    }

    mutating func pop() -> Job? {
        guard !a.isEmpty else { return nil }
        if a.count == 1 { return a.removeLast() }
        let top = a[0]
        a[0] = a.removeLast()
        siftDown(0)
        return top
    }

    private func higher(_ x: Job, _ y: Job) -> Bool {
        if x.dur != y.dur { return x.dur < y.dur }
        if x.req != y.req { return x.req < y.req }
        return x.id < y.id
    }

    private mutating func siftUp(_ i0: Int) {
        var i = i0
        while i > 0 {
            let p = (i - 1) / 2
            if higher(a[i], a[p]) {
                a.swapAt(i, p)
                i = p
            } else { break }
        }
    }

    private mutating func siftDown(_ i0: Int) {
        var i = i0
        while true {
            let l = i * 2 + 1
            let r = l + 1
            var best = i

            if l < a.count && higher(a[l], a[best]) { best = l }
            if r < a.count && higher(a[r], a[best]) { best = r }
            if best == i { break }

            a.swapAt(i, best)
            i = best
        }
    }
}

func solution(_ jobs: [[Int]]) -> Int {
    let n = jobs.count
    if n == 0 { return 0 }

    // 요청시각 기준으로 정렬하되, id(작업번호) 보존
    var arr: [(req: Int, dur: Int, id: Int)] = []
    arr.reserveCapacity(n)
    for (i, j) in jobs.enumerated() {
        arr.append((j[0], j[1], i))
    }
    arr.sort {
        if $0.req != $1.req { return $0.req < $1.req }
        if $0.dur != $1.dur { return $0.dur < $1.dur }
        return $0.id < $1.id
    }

    var heap = MinHeap()
    var time = 0
    var idx = 0
    var done = 0
    var totalTurnaround = 0

    while done < n {
        // 현재 시각까지 들어온 요청을 모두 큐에 넣기
        while idx < n && arr[idx].req <= time {
            heap.push(Job(dur: arr[idx].dur, req: arr[idx].req, id: arr[idx].id))
            idx += 1
        }

        if heap.isEmpty {
            // 대기 큐가 비면 다음 요청 시각으로 점프
            time = max(time, arr[idx].req)
            continue
        }

        // 우선순위 가장 높은 작업 수행 (non-preemptive)
        let job = heap.pop()!
        time += job.dur
        totalTurnaround += (time - job.req)
        done += 1
    }

    return totalTurnaround / n
}