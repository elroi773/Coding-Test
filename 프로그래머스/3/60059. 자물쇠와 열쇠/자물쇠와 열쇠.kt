class Solution {

    private fun rotate90(a: Array<IntArray>): Array<IntArray> {
        val n = a.size
        val r = Array(n) { IntArray(n) }
        for (i in 0 until n) {
            for (j in 0 until n) {
                r[j][n - 1 - i] = a[i][j]
            }
        }
        return r
    }

    private fun isUnlocked(board: Array<IntArray>, pad: Int, lockSize: Int): Boolean {
        for (i in 0 until lockSize) {
            for (j in 0 until lockSize) {
                if (board[i + pad][j + pad] != 1) return false
            }
        }
        return true
    }

    fun solution(key: Array<IntArray>, lock: Array<IntArray>): Boolean {
        val m = key.size
        val n = lock.size

        val pad = m - 1
        val size = n + pad * 2

        val board = Array(size) { IntArray(size) }

        // 중앙에 lock 배치
        for (i in 0 until n) {
            for (j in 0 until n) {
                board[i + pad][j + pad] = lock[i][j]
            }
        }

        var curKey = key

        repeat(4) {
            for (x in 0..(size - m)) {
                for (y in 0..(size - m)) {

                    // key를 board에 더하기
                    for (i in 0 until m) {
                        for (j in 0 until m) {
                            board[x + i][y + j] += curKey[i][j]
                        }
                    }

                    if (isUnlocked(board, pad, n)) return true

                    // 원복
                    for (i in 0 until m) {
                        for (j in 0 until m) {
                            board[x + i][y + j] -= curKey[i][j]
                        }
                    }
                }
            }
            curKey = rotate90(curKey)
        }

        return false
    }
}