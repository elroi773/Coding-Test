import kotlin.math.sqrt
import kotlin.math.floor

class Solution {
    fun solution(k: Int, d: Int): Long {
        var answer = 0L

        var x = 0L
        while (x <= d) {
            // 가능한 y의 최대 거리 계산
            val maxY = floor(sqrt((d.toLong() * d - x * x).toDouble())).toLong()
            // y는 0, k, 2k, ... maxY 이하이므로 개수는 (maxY / k) + 1
            answer += (maxY / k) + 1
            x += k
        }

        return answer
    }
}
