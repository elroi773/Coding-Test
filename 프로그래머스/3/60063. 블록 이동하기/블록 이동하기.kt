import java.util.ArrayDeque
import kotlin.math.*

class Solution {

    private data class State(val r1: Int, val c1: Int, val r2: Int, val c2: Int, val t: Int)

    // normalize so (r1,c1) <= (r2,c2) lexicographically
    private fun norm(r1: Int, c1: Int, r2: Int, c2: Int): IntArray {
        return if (r1 < r2 || (r1 == r2 && c1 <= c2)) {
            intArrayOf(r1, c1, r2, c2)
        } else {
            intArrayOf(r2, c2, r1, c1)
        }
    }

    // encode for visited (each coord <= 101 with padding, fits in 7 bits)
    private fun encode(r1: Int, c1: Int, r2: Int, c2: Int): Int {
        return (r1 shl 21) or (c1 shl 14) or (r2 shl 7) or c2
    }

    fun solution(board: Array<IntArray>): Int {
        val n = board.size

        // padding with walls
        val b = Array(n + 2) { IntArray(n + 2) { 1 } }
        for (i in 0 until n) {
            for (j in 0 until n) {
                b[i + 1][j + 1] = board[i][j]
            }
        }

        fun isGoal(r1: Int, c1: Int, r2: Int, c2: Int): Boolean {
            return (r1 == n && c1 == n) || (r2 == n && c2 == n)
        }

        val start = norm(1, 1, 1, 2)
        val q: ArrayDeque<State> = ArrayDeque()
        q.add(State(start[0], start[1], start[2], start[3], 0))

        val visited = HashSet<Int>()
        visited.add(encode(start[0], start[1], start[2], start[3]))

        val dr = intArrayOf(-1, 1, 0, 0)
        val dc = intArrayOf(0, 0, -1, 1)

        while (q.isNotEmpty()) {
            val cur = q.removeFirst()
            val r1 = cur.r1; val c1 = cur.c1; val r2 = cur.r2; val c2 = cur.c2
            val t = cur.t

            if (isGoal(r1, c1, r2, c2)) return t

            // 1) moves
            for (k in 0..3) {
                val nr1 = r1 + dr[k]
                val nc1 = c1 + dc[k]
                val nr2 = r2 + dr[k]
                val nc2 = c2 + dc[k]
                if (b[nr1][nc1] == 0 && b[nr2][nc2] == 0) {
                    val nxt = norm(nr1, nc1, nr2, nc2)
                    val code = encode(nxt[0], nxt[1], nxt[2], nxt[3])
                    if (visited.add(code)) {
                        q.add(State(nxt[0], nxt[1], nxt[2], nxt[3], t + 1))
                    }
                }
            }

            // 2) rotations
            // horizontal
            if (r1 == r2) {
                for (dir in intArrayOf(-1, 1)) { // up / down
                    if (b[r1 + dir][c1] == 0 && b[r2 + dir][c2] == 0) {
                        // pivot at (r1,c1)
                        run {
                            val nxt = norm(r1, c1, r1 + dir, c1)
                            val code = encode(nxt[0], nxt[1], nxt[2], nxt[3])
                            if (visited.add(code)) q.add(State(nxt[0], nxt[1], nxt[2], nxt[3], t + 1))
                        }
                        // pivot at (r2,c2)
                        run {
                            val nxt = norm(r2, c2, r2 + dir, c2)
                            val code = encode(nxt[0], nxt[1], nxt[2], nxt[3])
                            if (visited.add(code)) q.add(State(nxt[0], nxt[1], nxt[2], nxt[3], t + 1))
                        }
                    }
                }
            }

            // vertical
            if (c1 == c2) {
                for (dir in intArrayOf(-1, 1)) { // left / right
                    if (b[r1][c1 + dir] == 0 && b[r2][c2 + dir] == 0) {
                        // pivot at (r1,c1)
                        run {
                            val nxt = norm(r1, c1, r1, c1 + dir)
                            val code = encode(nxt[0], nxt[1], nxt[2], nxt[3])
                            if (visited.add(code)) q.add(State(nxt[0], nxt[1], nxt[2], nxt[3], t + 1))
                        }
                        // pivot at (r2,c2)
                        run {
                            val nxt = norm(r2, c2, r2, c2 + dir)
                            val code = encode(nxt[0], nxt[1], nxt[2], nxt[3])
                            if (visited.add(code)) q.add(State(nxt[0], nxt[1], nxt[2], nxt[3], t + 1))
                        }
                    }
                }
            }
        }

        return -1 // safety (problem guarantees reachable)
    }
}