import java.util.*

class Solution {

    fun solution(n: Long, bans: Array<String>): String {

        // 26의 거듭제곱 미리 계산
        val pow26 = LongArray(12)
        pow26[0] = 1L
        for (i in 1..11) {
            pow26[i] = pow26[i - 1] * 26L
        }

        // 문자열 → 순서 번호 (1-based)
        fun getRank(s: String): Long {
            val L = s.length
            var r = 0L

            // 더 짧은 문자열 개수
            for (l in 1 until L) {
                r += pow26[l]
            }

            // 같은 길이에서 사전순
            for (i in 0 until L) {
                r += (s[i] - 'a') * pow26[L - i - 1]
            }

            return r + 1
        }

        // 순서 번호 → 문자열
        fun getString(k0: Long): String {
            var k = k0
            var L = 1
            while (k > pow26[L]) {
                k -= pow26[L]
                L++
            }

            k--  // 0-based
            val sb = StringBuilder()

            for (i in 0 until L) {
                val div = pow26[L - i - 1]
                sb.append(('a'.code + (k / div).toInt()).toChar())
                k %= div
            }

            return sb.toString()
        }

        // bans → rank 배열
        val banned = LongArray(bans.size)
        for (i in bans.indices) {
            banned[i] = getRank(bans[i])
        }

        // 정렬
        banned.sort()

        // n 보정
        var target = n
        for (r in banned) {
            if (r <= target) target++
            else break
        }

        return getString(target)
    }
}
