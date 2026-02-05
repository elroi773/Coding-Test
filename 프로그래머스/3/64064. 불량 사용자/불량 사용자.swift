import Foundation

func solution(_ user_id: [String], _ banned_id: [String]) -> Int {
    // 패턴 매칭 함수: 길이가 같고, '*'는 와일드카드
    func isMatch(_ user: String, _ pattern: String) -> Bool {
        if user.count != pattern.count { return false }
        let u = Array(user)
        let p = Array(pattern)
        for i in 0..<p.count {
            if p[i] == "*" { continue }
            if p[i] != u[i] { return false }
        }
        return true
    }

    // banned_id 각 패턴별로 매칭 가능한 user 인덱스 후보를 미리 계산
    var candidates = Array(repeating: [Int](), count: banned_id.count)
    for (bi, pat) in banned_id.enumerated() {
        for (ui, user) in user_id.enumerated() {
            if isMatch(user, pat) {
                candidates[bi].append(ui)
            }
        }
    }

    // 결과 집합(순서 무관) 저장: 선택된 user들의 비트마스크
    var result = Set<Int>()

    func dfs(_ idx: Int, _ usedMask: Int) {
        if idx == banned_id.count {
            result.insert(usedMask)
            return
        }

        for ui in candidates[idx] {
            let bit = 1 << ui
            if (usedMask & bit) != 0 { continue } // 이미 사용된 유저면 스킵
            dfs(idx + 1, usedMask | bit)
        }
    }

    dfs(0, 0)
    return result.count
}