class Solution {
    lateinit var col: BooleanArray
    lateinit var diag1: BooleanArray
    lateinit var diag2: BooleanArray
    var answer = 0

    fun solution(n: Int): Int {
        col = BooleanArray(n)
        diag1 = BooleanArray(2 * n)
        diag2 = BooleanArray(2 * n)

        backtrack(0, n)
        return answer
    }

    fun backtrack(row: Int, n: Int) {
        if (row == n) {
            answer++
            return
        }

        for (c in 0 until n) {
            if (col[c] || diag1[row - c + n] || diag2[row + c]) continue

            // 퀸 배치
            col[c] = true
            diag1[row - c + n] = true
            diag2[row + c] = true

            backtrack(row + 1, n)

            // 백트래킹
            col[c] = false
            diag1[row - c + n] = false
            diag2[row + c] = false
        }
    }
}
