import Foundation

func solution(_ begin: String, _ target: String, _ words: [String]) -> Int {
    // target이 목록에 없으면 변환 불가
    if !words.contains(target) { return 0 }

    // begin도 포함해서 후보 단어 목록 구성
    let all = [begin] + words
    let n = all.count

    // 두 단어가 1글자만 다른지 체크
    func diffOne(_ a: String, _ b: String) -> Bool {
        let aa = Array(a), bb = Array(b)
        var diff = 0
        for i in 0..<aa.count {
            if aa[i] != bb[i] {
                diff += 1
                if diff > 1 { return false }
            }
        }
        return diff == 1
    }

    let targetIdx = all.firstIndex(of: target)! // 존재 보장

    // BFS
    var dist = Array(repeating: -1, count: n)
    dist[0] = 0

    var q = Array(repeating: 0, count: n)
    var head = 0, tail = 0
    q[tail] = 0; tail += 1

    while head < tail {
        let cur = q[head]; head += 1
        if cur == targetIdx { return dist[cur] }

        for nxt in 0..<n {
            if dist[nxt] == -1 && diffOne(all[cur], all[nxt]) {
                dist[nxt] = dist[cur] + 1
                q[tail] = nxt; tail += 1
            }
        }
    }

    return 0
}