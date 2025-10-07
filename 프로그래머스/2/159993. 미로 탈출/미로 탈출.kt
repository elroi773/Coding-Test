import java.util.*

class Solution {
    fun solution(maps: Array<String>): Int {
        val n = maps.size
        val m = maps[0].length

        var start = Pair(0, 0)
        var lever = Pair(0, 0)
        var exit = Pair(0, 0)

        // S, L, E 위치 찾기
        for (i in 0 until n) {
            for (j in 0 until m) {
                when (maps[i][j]) {
                    'S' -> start = Pair(i, j)
                    'L' -> lever = Pair(i, j)
                    'E' -> exit = Pair(i, j)
                }
            }
        }

        // BFS 함수: 시작점에서 목표까지 최소 거리 반환, 도달 불가 시 -1
        fun bfs(start: Pair<Int, Int>, target: Pair<Int, Int>): Int {
            val visited = Array(n) { BooleanArray(m) { false } }
            val queue: Queue<Triple<Int, Int, Int>> = LinkedList()
            val directions = arrayOf(
                intArrayOf(1,0), intArrayOf(-1,0),
                intArrayOf(0,1), intArrayOf(0,-1)
            )

            queue.add(Triple(start.first, start.second, 0))
            visited[start.first][start.second] = true

            while (queue.isNotEmpty()) {
                val (x, y, dist) = queue.poll()
                if (x == target.first && y == target.second) return dist

                for ((dx, dy) in directions) {
                    val nx = x + dx
                    val ny = y + dy
                    if (nx in 0 until n && ny in 0 until m && !visited[nx][ny] && maps[nx][ny] != 'X') {
                        visited[nx][ny] = true
                        queue.add(Triple(nx, ny, dist + 1))
                    }
                }
            }
            return -1
        }

        val distToLever = bfs(start, lever)
        if (distToLever == -1) return -1

        val distToExit = bfs(lever, exit)
        if (distToExit == -1) return -1

        return distToLever + distToExit
    }
}
