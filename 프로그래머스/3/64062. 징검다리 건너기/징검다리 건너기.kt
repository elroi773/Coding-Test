class Solution {
    fun solution(stones: IntArray, k: Int): Int {

        fun canCross(m: Int): Boolean {
            var consecutive = 0
            for (s in stones) {
                if (s < m) { // m명이 지나가면 0 이하가 되어 밟을 수 없는 돌
                    consecutive++
                    if (consecutive >= k) return false
                } else {
                    consecutive = 0
                }
            }
            return true
        }

        var left = 1
        var right = stones.maxOrNull() ?: 0
        var answer = 0

        while (left <= right) {
            val mid = left + (right - left) / 2
            if (canCross(mid)) {
                answer = mid      // mid명 가능 -> 더 큰 값 탐색
                left = mid + 1
            } else {
                right = mid - 1   // mid명 불가능 -> 더 작은 값 탐색
            }
        }
        return answer
    }
}