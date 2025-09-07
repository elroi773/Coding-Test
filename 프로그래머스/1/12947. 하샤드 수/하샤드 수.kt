class Solution {
    fun solution(x: Int): Boolean {
        // 자릿수 합 구하기
        var sum = 0
        var n = x
        while (n > 0) {
            sum += n % 10   // 마지막 자리 더하기
            n /= 10         // 다음 자리로 이동
        }
        // 하샤드 수 판별
        return x % sum == 0
    }
}
