class Solution {
    fun solution(k: Int, ranges: Array<IntArray>): DoubleArray {
        // 1️⃣ 우박수열 생성
        val seq = mutableListOf<Double>()
        var cur = k.toDouble()
        seq.add(cur)
        while (cur != 1.0) {
            cur = if (cur % 2 == 0.0) cur / 2 else cur * 3 + 1
            seq.add(cur)
        }

        val n = seq.size - 1  // 구간 개수

        // 2️⃣ 각 구간 넓이 계산 및 누적합(prefix sum)
        val prefix = DoubleArray(n + 1)
        prefix[0] = 0.0
        for (i in 0 until n) {
            val area = (seq[i] + seq[i + 1]) / 2.0
            prefix[i + 1] = prefix[i] + area
        }

        // 3️⃣ 각 ranges에 대한 정적분 계산
        val result = DoubleArray(ranges.size)
        for (i in ranges.indices) {
            val a = ranges[i][0]
            val b = ranges[i][1]
            val right = n + b

            if (a > right || a < 0 || right < 0 || a > n) {
                result[i] = -1.0
            } else {
                result[i] = prefix[right] - prefix[a]
            }
        }

        return result
    }
}
