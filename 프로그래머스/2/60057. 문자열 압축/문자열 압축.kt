class Solution {
    fun solution(s: String): Int {
        val len = s.length

        // 길이가 1이면 압축 불가능 → 1
        if (len == 1) return 1

        var answer = Int.MAX_VALUE

        // 자르는 단위: 1 ~ len/2
        for (cut in 1..(len / 2)) {
            val compressed = StringBuilder()
            var prev = s.substring(0, cut)
            var count = 1

            var i = cut
            while (i < len) {
                val end = (i + cut).coerceAtMost(len)
                val chunk = s.substring(i, end)

                if (chunk == prev) {
                    count++
                } else {
                    if (count > 1) compressed.append(count)
                    compressed.append(prev)
                    prev = chunk
                    count = 1
                }
                i += cut
            }

            // 마지막 처리
            if (count > 1) compressed.append(count)
            compressed.append(prev)

            answer = minOf(answer, compressed.length)
        }

        return answer
    }
}
