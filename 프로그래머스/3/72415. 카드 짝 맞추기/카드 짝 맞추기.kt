import kotlin.math.abs
import kotlin.math.min

class Solution {
    private val dr = intArrayOf(-1, 1, 0, 0)
    private val dc = intArrayOf(0, 0, -1, 1)

    private fun idx(r: Int, c: Int) = r * 4 + c
    private fun inRange(r: Int, c: Int) = r in 0..3 && c in 0..3

    private fun ctrlMove(from: Int, dir: Int, mask: Int): Int {
        var r = from / 4
        var c = from % 4
        while (true) {
            val nr = r + dr[dir]
            val nc = c + dc[dir]
            if (!inRange(nr, nc)) break
            r = nr; c = nc
            val ni = idx(r, c)
            if ((mask and (1 shl ni)) != 0) return ni // 카드 발견
        }
        return idx(r, c) // 끝 칸
    }

    private fun stepMove(from: Int, dir: Int): Int {
        val r = from / 4
        val c = from % 4
        val nr = r + dr[dir]
        val nc = c + dc[dir]
        if (!inRange(nr, nc)) return from
        return idx(nr, nc)
    }

    private fun dist(start: Int, target: Int, mask: Int): Int {
        if (start == target) return 0
        val vis = IntArray(16) { -1 }
        val q = IntArray(16)
        var head = 0
        var tail = 0
        q[tail++] = start
        vis[start] = 0

        while (head < tail) {
            val u = q[head++]
            val d = vis[u]
            for (dir in 0..3) {
                val v1 = stepMove(u, dir)
                if (vis[v1] == -1) {
                    vis[v1] = d + 1
                    if (v1 == target) return vis[v1]
                    q[tail++] = v1
                }
                val v2 = ctrlMove(u, dir, mask)
                if (vis[v2] == -1) {
                    vis[v2] = d + 1
                    if (v2 == target) return vis[v2]
                    q[tail++] = v2
                }
            }
        }
        return vis[target]
    }

    private fun key(mask: Int, cursor: Int): Long = (mask.toLong() shl 4) or cursor.toLong()

    private lateinit var types: IntArray
    private lateinit var pos: Array<IntArray> // pos[t] = intArrayOf(p1,p2)
    private val memo = HashMap<Long, Int>()

    private fun dfs(mask: Int, cursor: Int): Int {
        if (mask == 0) return 0
        val k = key(mask, cursor)
        memo[k]?.let { return it }

        var best = Int.MAX_VALUE

        for (t in types) {
            val p1 = pos[t][0]
            val p2 = pos[t][1]
            val has1 = (mask and (1 shl p1)) != 0
            val has2 = (mask and (1 shl p2)) != 0
            if (!has1 && !has2) continue

            val nextMask = mask and (1 shl p1).inv() and (1 shl p2).inv()

            // 순서 1: cursor -> p1 (Enter) -> p2 (Enter)
            val cost1 = dist(cursor, p1, mask) + 1 + dist(p1, p2, mask) + 1
            best = min(best, cost1 + dfs(nextMask, p2))

            // 순서 2: cursor -> p2 (Enter) -> p1 (Enter)
            val cost2 = dist(cursor, p2, mask) + 1 + dist(p2, p1, mask) + 1
            best = min(best, cost2 + dfs(nextMask, p1))
        }

        memo[k] = best
        return best
    }

    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        // 카드 위치 수집 + 마스크 구성
        val tmpPos = Array(7) { ArrayList<Int>(2) }
        var mask = 0
        val exist = BooleanArray(7)

        for (i in 0 until 4) {
            for (j in 0 until 4) {
                val v = board[i][j]
                if (v != 0) {
                    val p = idx(i, j)
                    mask = mask or (1 shl p)
                    exist[v] = true
                    tmpPos[v].add(p)
                }
            }
        }

        // types, pos 준비
        val typeList = ArrayList<Int>()
        for (v in 1..6) if (exist[v]) typeList.add(v)
        types = typeList.toIntArray()

        pos = Array(7) { intArrayOf(0, 0) }
        for (v in 1..6) {
            if (tmpPos[v].size == 2) {
                pos[v] = intArrayOf(tmpPos[v][0], tmpPos[v][1])
            }
        }

        memo.clear()
        return dfs(mask, idx(r, c))
    }
}