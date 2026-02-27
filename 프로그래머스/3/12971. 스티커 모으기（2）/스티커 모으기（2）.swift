import Foundation

func solveLinear(_ arr: [Int], _ l: Int, _ r: Int) -> Int { // inclusive [l..r]
    var prev2 = 0  // dp[i-2]
    var prev1 = 0  // dp[i-1]

    if l > r { return 0 }
    for i in l...r {
        let cur = max(prev1, prev2 + arr[i])
        prev2 = prev1
        prev1 = cur
    }
    return prev1
}

func solution(_ sticker: [Int]) -> Int {
    let n = sticker.count
    if n == 1 { return sticker[0] }
    if n == 2 { return max(sticker[0], sticker[1]) }

    // Case A: take first -> cannot take last => [0..n-2]
    let caseA = solveLinear(sticker, 0, n - 2)

    // Case B: skip first -> can take last => [1..n-1]
    let caseB = solveLinear(sticker, 1, n - 1)

    return max(caseA, caseB)
}