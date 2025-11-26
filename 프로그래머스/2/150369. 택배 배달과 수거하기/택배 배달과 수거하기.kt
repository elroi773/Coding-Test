class Solution {
    fun solution(cap: Int, n: Int, deliveries: IntArray, pickups: IntArray): Long {
        var answer: Long = 0
        var dIdx = n - 1 // 배달이 남은 마지막 집
        var pIdx = n - 1 // 수거가 남은 마지막 집

        while (dIdx >= 0 || pIdx >= 0) {
            // 남은 배달 마지막 집 찾기
            while (dIdx >= 0 && deliveries[dIdx] == 0) dIdx--
            // 남은 수거 마지막 집 찾기
            while (pIdx >= 0 && pickups[pIdx] == 0) pIdx--

            if (dIdx < 0 && pIdx < 0) break

            // 이번 회차 최대 거리
            val distance = maxOf(dIdx, pIdx) + 1
            answer += distance.toLong() * 2 // 왕복 거리

            // 배달 처리
            var load = cap
            var i = dIdx
            while (i >= 0 && load > 0) {
                if (deliveries[i] <= load) {
                    load -= deliveries[i]
                    deliveries[i] = 0
                    dIdx = i - 1
                } else {
                    deliveries[i] -= load
                    load = 0
                }
                i--
            }

            // 수거 처리
            load = cap
            i = pIdx
            while (i >= 0 && load > 0) {
                if (pickups[i] <= load) {
                    load -= pickups[i]
                    pickups[i] = 0
                    pIdx = i - 1
                } else {
                    pickups[i] -= load
                    load = 0
                }
                i--
            }
        }

        return answer
    }
}
