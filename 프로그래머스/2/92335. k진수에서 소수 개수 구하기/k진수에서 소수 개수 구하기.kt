class Solution {
    fun solution(n: Int, k: Int): Int {

        // 1. n을 k진수 문자열로 변환
        fun toBaseK(num: Int, base: Int): String {
            if (num == 0) return "0"
            var x = num
            val sb = StringBuilder()
            while (x > 0) {
                sb.append(x % base)   // 0 ~ 9
                x /= base
            }
            return sb.reverse().toString()
        }

        // 2. 소수 판별
        fun isPrime(x: Long): Boolean {
            if (x < 2L) return false
            if (x == 2L) return true
            if (x % 2L == 0L) return false
            var i = 3L
            val limit = kotlin.math.sqrt(x.toDouble()).toLong()
            while (i <= limit) {
                if (x % i == 0L) return false
                i += 2
            }
            return true
        }

        val baseStr = toBaseK(n, k)          // 예: 437674, 3 -> "211020101011"
        val parts = baseStr.split("0+")      // 정규식 X, 그냥 "0"이 아니라:
            .flatMap { it.split("0") }       // 위 한 줄 대신 아래 한 줄로 충분
        // val parts = baseStr.split("0")    // 이렇게만 써도 됩니다 (연속 0 처리 OK)

        var answer = 0

        for (p in baseStr.split("0")) {
            if (p.isEmpty()) continue       // 빈 문자열 무시
            val num = p.toLong()           // '211' -> 211 (10진수로 해석)
            if (isPrime(num)) answer++
        }

        return answer
    }
}