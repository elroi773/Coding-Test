class Solution {
    fun solution(N: Int, number: Int): Int {
        if (N == number) return 1

        val dp = Array(9) { HashSet<Int>() } // dp[i] = N을 i번 사용해서 만들 수 있는 값들

        for (i in 1..8) {
            // 이어붙인 수: N, NN, NNN...
            var concat = 0
            repeat(i) { concat = concat * 10 + N }
            dp[i].add(concat)

            // j + (i-j)로 분할해서 사칙연산 조합
            for (j in 1 until i) {
                for (a in dp[j]) {
                    for (b in dp[i - j]) {
                        dp[i].add(a + b)
                        dp[i].add(a - b)
                        dp[i].add(a * b)
                        if (b != 0) dp[i].add(a / b) // 정수 나눗셈(나머지 무시)
                    }
                }
            }

            if (dp[i].contains(number)) return i
        }

        return -1
    }
}