class Solution {
    fun solution(n: Int, results: Array<IntArray>): Int {
        val win = Array(n + 1) { BooleanArray(n + 1) } // win[a][b] = a가 b를 이김

        for (r in results) {
            win[r[0]][r[1]] = true
        }

        // Floyd-Warshall: a>k and k>b => a>b
        for (k in 1..n) {
            for (a in 1..n) {
                if (!win[a][k]) continue
                for (b in 1..n) {
                    if (win[k][b]) win[a][b] = true
                }
            }
        }

        var answer = 0
        for (i in 1..n) {
            var known = 0
            for (j in 1..n) {
                if (i == j) continue
                if (win[i][j] || win[j][i]) known++
            }
            if (known == n - 1) answer++
        }

        return answer
    }
}