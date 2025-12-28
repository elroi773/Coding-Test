class Solution {
    fun solution(scores: Array<IntArray>): Int {
        val wanhoA = scores[0][0]
        val wanhoB = scores[0][1]
        val wanhoSum = wanhoA + wanhoB

        // 근무태도 내림차순, 동료평가 오름차순
        scores.sortWith(compareBy<IntArray>({ -it[0] }, { it[1] }))

        var maxB = 0
        var rank = 1

        for (s in scores) {
            val a = s[0]
            val b = s[1]

            // 지배당한 경우
            if (b < maxB) {
                if (a == wanhoA && b == wanhoB) {
                    return -1
                }
                continue
            }

            maxB = b

            if (a + b > wanhoSum) {
                rank++
            }
        }

        return rank
    }
}
