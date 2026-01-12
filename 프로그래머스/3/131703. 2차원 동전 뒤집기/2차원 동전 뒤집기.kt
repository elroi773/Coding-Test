class Solution {
    fun solution(beginning: Array<IntArray>, target: Array<IntArray>): Int {
        val r = beginning.size
        val c = beginning[0].size

        // diff = beginning XOR target
        val diff = Array(r) { IntArray(c) }
        for (i in 0 until r) {
            for (j in 0 until c) {
                diff[i][j] = beginning[i][j] xor target[i][j]
            }
        }

        var best = Int.MAX_VALUE
        val totalMasks = 1 shl r

        for (mask in 0 until totalMasks) {
            val colFlip = IntArray(c)
            val rf0 = mask and 1 // rowFlip(0)

            var colCount = 0
            for (j in 0 until c) {
                colFlip[j] = diff[0][j] xor rf0
                colCount += colFlip[j]
            }

            var ok = true
            for (i in 0 until r) {
                val rfi = (mask shr i) and 1
                for (j in 0 until c) {
                    if ((diff[i][j] xor rfi xor colFlip[j]) != 0) {
                        ok = false
                        break
                    }
                }
                if (!ok) break
            }

            if (ok) {
                val rowCount = Integer.bitCount(mask)
                best = minOf(best, rowCount + colCount)
            }
        }

        return if (best == Int.MAX_VALUE) -1 else best
    }
}