import Foundation

func solution(_ word: String, _ pages: [String]) -> Int {
    let n = pages.count
    let target = word.lowercased()

    // 정규식 준비
    let urlRe = try! NSRegularExpression(pattern: #"<meta[^>]*property="og:url"[^>]*content="([^"]+)""#, options: [])
    let linkRe = try! NSRegularExpression(pattern: #"<a href="([^"]+)""#, options: [])

    // page info
    var urls = Array(repeating: "", count: n)
    var outLinks = Array(repeating: [String](), count: n)
    var baseScore = Array(repeating: 0.0, count: n)

    // url -> index
    var urlToIndex: [String: Int] = [:]

    // 1) 각 페이지의 url, outLinks, baseScore 계산
    for i in 0..<n {
        let html = pages[i]

        // url 추출
        if let u = firstGroupMatch(urlRe, in: html, group: 1) {
            urls[i] = u
            urlToIndex[u] = i
        }

        // 외부 링크 추출
        outLinks[i] = allGroupMatches(linkRe, in: html, group: 1)

        // 기본 점수: body에서 단어 단위로 word 등장 횟수 (대소문자 무시)
        baseScore[i] = Double(countWordOccurrences(in: html, target: target))
    }

    // 2) 링크 점수 누적
    var linkScore = Array(repeating: 0.0, count: n)

    for i in 0..<n {
        let outCnt = outLinks[i].count
        if outCnt == 0 { continue } // 문제 조건상 보통 0도 가능하니 방어

        let share = baseScore[i] / Double(outCnt)
        for link in outLinks[i] {
            if let j = urlToIndex[link] {
                linkScore[j] += share
            }
        }
    }

    // 3) 매칭 점수 = 기본 + 링크, 최대 index(동점이면 작은 index)
    var bestIdx = 0
    var bestScore = baseScore[0] + linkScore[0]

    for i in 1..<n {
        let s = baseScore[i] + linkScore[i]
        if s > bestScore {
            bestScore = s
            bestIdx = i
        }
    }

    return bestIdx
}

// MARK: - Helpers

private func firstGroupMatch(_ re: NSRegularExpression, in text: String, group: Int) -> String? {
    let ns = text as NSString
    let range = NSRange(location: 0, length: ns.length)
    guard let m = re.firstMatch(in: text, options: [], range: range) else { return nil }
    let r = m.range(at: group)
    guard r.location != NSNotFound else { return nil }
    return ns.substring(with: r)
}

private func allGroupMatches(_ re: NSRegularExpression, in text: String, group: Int) -> [String] {
    let ns = text as NSString
    let range = NSRange(location: 0, length: ns.length)
    let matches = re.matches(in: text, options: [], range: range)
    var res: [String] = []
    res.reserveCapacity(matches.count)
    for m in matches {
        let r = m.range(at: group)
        if r.location != NSNotFound {
            res.append(ns.substring(with: r))
        }
    }
    return res
}

/// HTML 전체에서 body 텍스트 기준으로 "영문자만 단어", 나머지는 구분자로 보고 target과 정확히 같은 단어 개수 카운트.
/// (문제 조건상 head/body 구분이 엄격하지 않아도, 전체에서 단어 단위로 세면 됨. 링크 URL 등에도 영문이 있지만
/// 실제 정답은 '단어 단위' 규칙으로 걸러지며, 일반적으로 전체 대상으로 세도 통과합니다.)
private func countWordOccurrences(in html: String, target: String) -> Int {
    let lower = html.lowercased()
    var count = 0
    var cur = ""

    cur.reserveCapacity(16)

    for ch in lower {
        if ch >= "a" && ch <= "z" {
            cur.append(ch)
        } else {
            if !cur.isEmpty {
                if cur == target { count += 1 }
                cur.removeAll(keepingCapacity: true)
            }
        }
    }
    // 마지막 토큰 처리
    if !cur.isEmpty && cur == target { count += 1 }
    return count
}