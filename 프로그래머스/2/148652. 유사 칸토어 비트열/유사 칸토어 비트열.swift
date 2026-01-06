import Foundation

func solution(_ n: Int, _ l: Int64, _ r: Int64) -> Int {
    // 5^n, 4^n 미리 계산 (n <= 20)
    var pow5 = Array(repeating: Int64(1), count: 21)
    var pow4 = Array(repeating: Int64(1), count: 21)
    if 20 >= 1 {
        for i in 1...20 {
            pow5[i] = pow5[i - 1] * 5
            pow4[i] = pow4[i - 1] * 4
        }
    }

    // n번째 비트열의 1..x(1-base) 구간 내 '1'의 개수
    func prefix(_ n: Int, _ x: Int64) -> Int64 {
        if x <= 0 { return 0 }
        let len = pow5[n]
        if x >= len { return pow4[n] }   // 전체 길이를 넘으면 전체 1의 개수

        if n == 0 {
            // S(0) = "1" (길이 1)
            return 1
        }

        let segLen = pow5[n - 1]         // 각 블록 길이
        let onesSeg = pow4[n - 1]        // S(n-1) 안의 1 개수

        let full = x / segLen            // 완전히 포함되는 블록 개수 (0..4)
        let rem  = x % segLen            // 다음 블록에서의 나머지 길이

        var res: Int64 = 0

        // 블록 0,1,2,3,4 중 2번 블록만 전부 0
        if full >= 1 { res += onesSeg }  // block 0
        if full >= 2 { res += onesSeg }  // block 1
        // block 2: +0
        if full >= 4 { res += onesSeg }  // block 3 (full>=4면 0,1,2,3을 다 포함)
        if full >= 5 { res += onesSeg }  // block 4 (여긴 사실 x<len이면 거의 안 옴)

        // 부분 블록 처리
        if rem > 0 {
            if full == 2 {
                // 2번 블록은 전부 0이므로 추가 없음
            } else {
                res += prefix(n - 1, rem)
            }
        }

        return res
    }

    let ans = prefix(n, r) - prefix(n, l - 1)
    return Int(ans)
}