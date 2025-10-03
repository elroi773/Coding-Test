import java.util.*

class Solution {
    data class Node(val x: Int, val y: Int, val cnt: Int)

    // 상, 하, 좌, 우
    private val dx = intArrayOf(1, -1, 0, 0)
    private val dy = intArrayOf(0, 0, 1, -1)

    fun solution(board: Array<String>): Int {
        val n = board.size
        val m = board[0].length
        val visited = Array(n) { BooleanArray(m) }

        var startX = 0
        var startY = 0
        var goalX = 0
        var goalY = 0

        // 시작점(R), 목표점(G) 찾기
        for (i in 0 until n) {
            for (j in 0 until m) {
                when (board[i][j]) {
                    'R' -> {
                        startX = i
                        startY = j
                    }
                    'G' -> {
                        goalX = i
                        goalY = j
                    }
                }
            }
        }

        val queue: Queue<Node> = LinkedList()
        queue.add(Node(startX, startY, 0))
        visited[startX][startY] = true

        while (queue.isNotEmpty()) {
            val (x, y, cnt) = queue.poll()

            // 목표 도착
            if (x == goalX && y == goalY) return cnt

            // 4방향 탐색
            for (d in 0 until 4) {
                var nx = x
                var ny = y

                // 한 방향으로 끝까지 미끄러짐
                while (true) {
                    val tx = nx + dx[d]
                    val ty = ny + dy[d]

                    if (tx !in 0 until n || ty !in 0 until m) break // 보드 밖
                    if (board[tx][ty] == 'D') break // 장애물

                    nx = tx
                    ny = ty
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true
                    queue.add(Node(nx, ny, cnt + 1))
                }
            }
        }

        return -1 // 목표 도달 불가
    }
}
