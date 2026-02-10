import java.util.StringTokenizer

class Solution {
    fun solution(n: Int, build_frame: Array<IntArray>): Array<IntArray> {
        // 좌표 0..n, 보에서 x+1 접근하니 n+2로 여유
        val exist = Array(n + 2) { Array(n + 2) { BooleanArray(2) } }

        fun canPlace(x: Int, y: Int, a: Int): Boolean {
            return if (a == 0) {
                // 기둥: 바닥 OR 아래 기둥 OR 보 끝 위
                y == 0 ||
                        exist[x][y - 1][0] ||
                        (x > 0 && exist[x - 1][y][1]) ||
                        exist[x][y][1]
            } else {
                // 보: 한쪽 끝이 기둥 위 OR 양쪽 끝이 보로 연결
                (y > 0 && exist[x][y - 1][0]) ||
                        (y > 0 && exist[x + 1][y - 1][0]) ||
                        (x > 0 && exist[x - 1][y][1] && exist[x + 1][y][1])
            }
        }

        fun validAll(): Boolean {
            for (x in 0..n) {
                for (y in 0..n) {
                    for (a in 0..1) {
                        if (exist[x][y][a] && !canPlace(x, y, a)) return false
                    }
                }
            }
            return true
        }

        for (cmd in build_frame) {
            val x = cmd[0]
            val y = cmd[1]
            val a = cmd[2]
            val b = cmd[3]

            if (b == 1) { // 설치
                exist[x][y][a] = true
                if (!validAll()) exist[x][y][a] = false
            } else { // 삭제
                exist[x][y][a] = false
                if (!validAll()) exist[x][y][a] = true
            }
        }

        val result = ArrayList<IntArray>()
        for (x in 0..n) {
            for (y in 0..n) {
                for (a in 0..1) {
                    if (exist[x][y][a]) result.add(intArrayOf(x, y, a))
                }
            }
        }

        result.sortWith(compareBy<IntArray>({ it[0] }, { it[1] }, { it[2] }))

        return result.toTypedArray()
    }
}