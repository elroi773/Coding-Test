import Foundation

func solution(_ N: Int, _ number: Int) -> Int {
    if N == number { return 1 }

    var dp = Array(repeating: Set<Int>(), count: 9) // dp[i] = N을 i번 사용해서 만들 수 있는 값들

    for i in 1...8 {
        // 이어붙인 수: N, NN, NNN...
        let concat = Int(String(repeating: String(N), count: i))!
        dp[i].insert(concat)

        // j + (i-j)로 분할해서 조합
        if i >= 2 {
            for j in 1..<(i) {
                for a in dp[j] {
                    for b in dp[i - j] {
                        dp[i].insert(a + b)
                        dp[i].insert(a - b)
                        dp[i].insert(a * b)
                        if b != 0 {
                            dp[i].insert(a / b) // 정수 나눗셈(나머지 무시)
                        }
                    }
                }
            }
        }

        if dp[i].contains(number) { return i }
    }

    return -1
}