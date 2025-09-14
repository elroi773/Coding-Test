class Solution {
    fun solution(n: Int): Int {
        var answer = 0

        if (n % 2 == 1) { // 홀수일 때
            for (i in 1..n step 2) {
                answer += i
            }
        } else { // 짝수일 때
            for (i in 2..n step 2) {
                answer += i * i
            }
        }

        return answer
    }
}
