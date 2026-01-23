class Solution {
    fun solution(a: Int, b: Int, g: IntArray, s: IntArray, w: IntArray, t: IntArray): Long {
        val needG = a.toLong()
        val needS = b.toLong()
        val needT = needG + needS

        fun can(time: Long): Boolean {
            var sumG = 0L
            var sumS = 0L
            var sumAll = 0L

            for (i in g.indices) {
                val gi = g[i].toLong()
                val si = s[i].toLong()
                val wi = w[i].toLong()
                val ti = t[i].toLong()

                val round = ti * 2L
                var trips = time / round
                if (time % round >= ti) trips += 1L // 마지막 편도 1회 가능

                val cap = trips * wi

                sumG += minOf(gi, cap)
                sumS += minOf(si, cap)
                sumAll += minOf(gi + si, cap)

                if (sumG > needG) sumG = needG
                if (sumS > needS) sumS = needS
                if (sumAll > needT) sumAll = needT
            }

            return sumG >= needG && sumS >= needS && sumAll >= needT
        }

        var lo = 0L
        var hi = 4_000_000_000_000_000L // 4e15

        while (lo < hi) {
            val mid = (lo + hi) / 2L
            if (can(mid)) hi = mid
            else lo = mid + 1L
        }

        return lo
    }
}