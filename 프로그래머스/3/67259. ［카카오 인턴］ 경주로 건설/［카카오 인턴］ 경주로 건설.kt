import java.util.PriorityQueue

class Solution {
    data class State(val cost: Int, val x: Int, val y: Int, val dir: Int) // dir: 0=up,1=down,2=left,3=right, -1=start

    fun solution(board: Array<IntArray>): Int {
        val n = board.size
        val INF = 1_000_000_000

        val dx = intArrayOf(-1, 1, 0, 0)
        val dy = intArrayOf(0, 0, -1, 1)

        // dist[x][y][dir] = (x,y)에 dir 방향으로 "들어온" 최소 비용
        val dist = Array(n) { Array(n) { IntArray(4) { INF } } }

        val pq = PriorityQueue<State>(compareBy { it.cost })
        // 시작점: 이전 방향이 없으므로 dir=-1, 첫 이동은 무조건 +100
        pq.offer(State(0, 0, 0, -1))

        while (pq.isNotEmpty()) {
            val cur = pq.poll()

            for (nd in 0 until 4) {
                val nx = cur.x + dx[nd]
                val ny = cur.y + dy[nd]
                if (nx !in 0 until n || ny !in 0 until n) continue
                if (board[nx][ny] == 1) continue

                val add = if (cur.dir == -1 || cur.dir == nd) 100 else 600 // 직선 100, 코너 600(100+500)
                val newCost = cur.cost + add

                if (newCost < dist[nx][ny][nd]) {
                    dist[nx][ny][nd] = newCost
                    pq.offer(State(newCost, nx, ny, nd))
                }
            }
        }

        return dist[n - 1][n - 1].minOrNull()!!
    }
}