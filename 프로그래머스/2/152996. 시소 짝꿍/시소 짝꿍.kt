class Solution {
    fun solution(weights: IntArray): Long {
        var answer: Long = 0
        val count = mutableMapOf<Int, Long>()

        // 1️⃣ 몸무게별 등장 횟수 카운트
        for (w in weights) {
            count[w] = count.getOrDefault(w, 0L) + 1
        }

        // 2️⃣ 가능한 비율 (분수 형태)
        val ratios = arrayOf(
            intArrayOf(1, 1), // same
            intArrayOf(3, 2), // 1.5
            intArrayOf(2, 1), // 2.0
            intArrayOf(4, 3)  // 1.333...
        )

        // 3️⃣ 각 몸무게에 대해 짝 찾기
        for ((w, cnt) in count) {
            // 같은 몸무게끼리 짝꿍 (조합 nC2)
            if (cnt > 1) {
                answer += cnt * (cnt - 1) / 2
            }

            // 다른 비율로 짝꿍 찾기
            for (i in 1 until ratios.size) { // ratio[0]은 1:1이므로 제외
                val (num, den) = ratios[i]
                if ((w * num) % den != 0) continue // 정수만 허용

                val target = (w * num) / den
                if (count.containsKey(target)) {
                    answer += cnt * count[target]!!
                }
            }
        }

        return answer
    }
}

