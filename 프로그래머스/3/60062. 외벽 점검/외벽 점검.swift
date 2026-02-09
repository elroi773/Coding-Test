import Foundation

func solution(_ n: Int, _ weak: [Int], _ dist: [Int]) -> Int {
    let m = weak.count
    if m == 0 { return 0 }

    // 원형 -> 선형으로 펴기
    var extended = weak
    extended += weak.map { $0 + n }

    // dist 정렬(중복 순열 가지치기 도움)
    let distSorted = dist.sorted()
    let dLen = distSorted.count

    var best = Int.max

    // 커버 가능 여부: friends(순열 prefix)로 segment(weak m개)를 모두 덮을 수 있는지
    func canCover(_ segment: [Int], _ friends: [Int]) -> Bool {
        var usedCnt = 1
        var coverageEnd = segment[0] + friends[0]

        for w in segment {
            if w > coverageEnd {
                usedCnt += 1
                if usedCnt > friends.count { return false }
                coverageEnd = w + friends[usedCnt - 1]
            }
        }
        return true
    }

    // 순열 DFS
    var used = Array(repeating: false, count: dLen)
    var perm = Array(repeating: 0, count: dLen)

    func dfs(_ depth: Int, _ segment: [Int]) {
        if depth >= best { return } // 가지치기

        if depth > 0 {
            let friends = Array(perm[0..<depth])
            if canCover(segment, friends) {
                best = min(best, depth)
                return
            }
        }

        if depth == dLen { return }

        var prev: Int? = nil
        for i in 0..<dLen {
            if used[i] { continue }
            if let p = prev, p == distSorted[i] { continue } // 중복 제거
            prev = distSorted[i]

            used[i] = true
            perm[depth] = distSorted[i]
            dfs(depth + 1, segment)
            used[i] = false
        }
    }

    // 시작점을 weak의 각 지점으로 잡고 탐색
    for start in 0..<m {
        let segment = Array(extended[start..<(start + m)])
        used = Array(repeating: false, count: dLen)
        dfs(0, segment)
    }

    return best == Int.max ? -1 : best
}