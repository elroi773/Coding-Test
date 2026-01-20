import kotlin.math.*

class Solution {
    fun solution(target: Int): IntArray {
        val INF = 1_000_000_000

        // (score, singleOrBullCount)
        val scores = IntArray(61)
        val scnt = IntArray(61)
        var k = 0

        for (i in 1..20) {
            scores[k] = i; scnt[k] = 1; k++       // single
            scores[k] = i * 2; scnt[k] = 0; k++   // double
            scores[k] = i * 3; scnt[k] = 0; k++   // triple
        }
        scores[k] = 50; scnt[k] = 1; k++          // bull

        val dpDarts = IntArray(target + 1) { INF }
        val dpSingles = IntArray(target + 1) { -INF }

        dpDarts[0] = 0
        dpSingles[0] = 0

        for (t in 1..target) {
            for (i in 0 until k) {
                val s = scores[i]
                if (t >= s && dpDarts[t - s] != INF) {
                    val candDarts = dpDarts[t - s] + 1
                    val candSingles = dpSingles[t - s] + scnt[i]

                    if (candDarts < dpDarts[t] ||
                        (candDarts == dpDarts[t] && candSingles > dpSingles[t])
                    ) {
                        dpDarts[t] = candDarts
                        dpSingles[t] = candSingles
                    }
                }
            }
        }

        return intArrayOf(dpDarts[target], dpSingles[target])
    }
}