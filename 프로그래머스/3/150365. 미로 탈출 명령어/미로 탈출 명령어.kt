class Solution {
    private fun dist(x1: Int, y1: Int, x2: Int, y2: Int): Int =
        kotlin.math.abs(x1 - x2) + kotlin.math.abs(y1 - y2)

    fun solution(n: Int, m: Int, x: Int, y: Int, r: Int, c: Int, k: Int): String {
        val d0 = dist(x, y, r, c)
        if (d0 > k || ((k - d0) and 1) == 1) return "impossible"

        var cx = x
        var cy = y
        val sb = StringBuilder(k)

        val ch = charArrayOf('d', 'l', 'r', 'u')
        val dx = intArrayOf( 1,  0,  0, -1)
        val dy = intArrayOf( 0, -1,  1,  0)

        for (step in 0 until k) {
            var moved = false
            for (i in 0..3) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]
                if (nx !in 1..n || ny !in 1..m) continue

                val rem = k - (step + 1)
                val nd = dist(nx, ny, r, c)
                if (nd <= rem && ((rem - nd) and 1) == 0) {
                    sb.append(ch[i])
                    cx = nx; cy = ny
                    moved = true
                    break
                }
            }
            if (!moved) return "impossible"
        }

        return sb.toString()
    }
}