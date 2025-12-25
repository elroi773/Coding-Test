class Solution {
    private var n = 0
    private var half = 0
    private var maxWin = -1
    private lateinit var bestPick: IntArray

    fun solution(dice: Array<IntArray>): IntArray {
        n = dice.size
        half = n / 2
        bestPick = IntArray(half)

        val used = BooleanArray(n)
        val pick = IntArray(half)

        dfs(dice, 0, 0, pick, used)

        return IntArray(half) { bestPick[it] + 1 } // 1-based index
    }

    private fun dfs(
        dice: Array<IntArray>,
        idx: Int,
        cnt: Int,
        pick: IntArray,
        used: BooleanArray
    ) {
        if (cnt == half) {
            val aIdxs = mutableListOf<Int>()
            val bIdxs = mutableListOf<Int>()

            for (i in 0 until n) {
                if (used[i]) aIdxs.add(i)
                else bIdxs.add(i)
            }

            val aDist = getDistribution(dice, aIdxs)
            val bDist = getDistribution(dice, bIdxs)

            val win = countWin(aDist, bDist)

            if (win > maxWin) {
                maxWin = win
                for (i in 0 until half) {
                    bestPick[i] = pick[i]
                }
            }
            return
        }

        for (i in idx until n) {
            used[i] = true
            pick[cnt] = i
            dfs(dice, i + 1, cnt + 1, pick, used)
            used[i] = false
        }
    }

    private fun getDistribution(
        dice: Array<IntArray>,
        idxs: List<Int>
    ): Map<Int, Int> {
        var map = mutableMapOf(0 to 1)

        for (idx in idxs) {
            val next = mutableMapOf<Int, Int>()
            for ((sum, cnt) in map) {
                for (face in dice[idx]) {
                    val ns = sum + face
                    next[ns] = (next[ns] ?: 0) + cnt
                }
            }
            map = next
        }
        return map
    }

    private fun countWin(
        aDist: Map<Int, Int>,
        bDist: Map<Int, Int>
    ): Int {
        val maxB = bDist.keys.maxOrNull() ?: 0

        val prefix = IntArray(maxB + 2)
        for ((b, cnt) in bDist) {
            prefix[b + 1] += cnt
        }
        for (i in 1 until prefix.size) {
            prefix[i] += prefix[i - 1]
        }

        var win = 0
        for ((a, cntA) in aDist) {
            win += if (a <= maxB) cntA * prefix[a]
                   else cntA * prefix[maxB + 1]
        }
        return win
    }
}
