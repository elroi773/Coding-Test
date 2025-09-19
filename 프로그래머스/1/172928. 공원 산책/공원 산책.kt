class Solution {
    fun solution(park: Array<String>, routes: Array<String>): IntArray {
        val h = park.size
        val w = park[0].length

        // 시작 좌표 찾기
        var x = 0
        var y = 0
        for (i in park.indices) {
            for (j in park[i].indices) {
                if (park[i][j] == 'S') {
                    x = i
                    y = j
                }
            }
        }

        // 방향 정의
        val dir = mapOf(
            "N" to Pair(-1, 0),
            "S" to Pair(1, 0),
            "W" to Pair(0, -1),
            "E" to Pair(0, 1)
        )

        // 명령 실행
        for (r in routes) {
            val (d, nStr) = r.split(" ")
            val n = nStr.toInt()
            val dx = dir[d]!!.first
            val dy = dir[d]!!.second

            var nx = x
            var ny = y
            var canMove = true

            for (step in 1..n) {
                val tx = x + dx * step
                val ty = y + dy * step

                if (tx !in 0 until h || ty !in 0 until w || park[tx][ty] == 'X') {
                    canMove = false
                    break
                }
                nx = tx
                ny = ty
            }

            if (canMove) {
                x = nx
                y = ny
            }
        }

        return intArrayOf(x, y)
    }
}
