import Foundation

func solution(_ target: Int) -> [Int] {
    let INF = 1_000_000_000

    // throws: (score, singleOrBullCount)
    var throwsList: [(Int, Int)] = []
    for i in 1...20 {
        throwsList.append((i, 1))      // single
        throwsList.append((i * 2, 0))  // double
        throwsList.append((i * 3, 0))  // triple
    }
    throwsList.append((50, 1))         // bull

    var minDarts = Array(repeating: INF, count: target + 1)
    var maxSingles = Array(repeating: -INF, count: target + 1)

    minDarts[0] = 0
    maxSingles[0] = 0

    if target >= 1 {
        for t in 1...target {
            for (score, sCount) in throwsList {
                if t >= score && minDarts[t - score] != INF {
                    let candDarts = minDarts[t - score] + 1
                    let candSingles = maxSingles[t - score] + sCount

                    if candDarts < minDarts[t] ||
                        (candDarts == minDarts[t] && candSingles > maxSingles[t]) {
                        minDarts[t] = candDarts
                        maxSingles[t] = candSingles
                    }
                }
            }
        }
    }

    return [minDarts[target], maxSingles[target]]
}