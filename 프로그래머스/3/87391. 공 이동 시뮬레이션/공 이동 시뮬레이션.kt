class Solution {
    fun solution(n: Int, m: Int, x: Int, y: Int, queries: Array<IntArray>): Long {
        var r1 = x.toLong()
        var r2 = x.toLong()
        var c1 = y.toLong()
        var c2 = y.toLong()

        for (i in queries.size - 1 downTo 0) {
            val cmd = queries[i][0]
            val dx = queries[i][1].toLong()

            when (cmd) {
                0 -> { // (정방향) 왼쪽 -> (역방향) 오른쪽
                    if (c1 != 0L) c1 += dx
                    c2 = minOf(m.toLong() - 1L, c2 + dx)
                }
                1 -> { // (정방향) 오른쪽 -> (역방향) 왼쪽
                    c1 = maxOf(0L, c1 - dx)
                    if (c2 != m.toLong() - 1L) c2 -= dx
                }
                2 -> { // (정방향) 위 -> (역방향) 아래
                    if (r1 != 0L) r1 += dx
                    r2 = minOf(n.toLong() - 1L, r2 + dx)
                }
                3 -> { // (정방향) 아래 -> (역방향) 위
                    r1 = maxOf(0L, r1 - dx)
                    if (r2 != n.toLong() - 1L) r2 -= dx
                }
            }

            if (r1 > r2 || c1 > c2) return 0L
        }

        return (r2 - r1 + 1L) * (c2 - c1 + 1L)
    }
}