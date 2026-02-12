import java.util.Arrays

class Solution {
    fun solution(n: Int, times: IntArray): Long {
        Arrays.sort(times)

        var left = 1L
        var right = times[times.size - 1].toLong() * n.toLong() // 최악의 경우
        var answer = right

        while (left <= right) {
            val mid = left + (right - left) / 2

            var processed = 0L
            for (t in times) {
                processed += mid / t.toLong()
                if (processed >= n.toLong()) break
            }

            if (processed >= n.toLong()) {
                answer = mid
                right = mid - 1
            } else {
                left = mid + 1
            }
        }

        return answer
    }
}