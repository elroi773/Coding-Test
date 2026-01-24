import java.util.ArrayDeque
import java.util.HashMap

class Solution {

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val n = game_board.size

        // table 블록(1) 도형 key별 개수
        val blocks = extractComponents(table, 1)
        val blockCount = HashMap<String, Int>()
        for (b in blocks) {
            val key = canonicalKey(b)
            blockCount[key] = (blockCount[key] ?: 0) + 1
        }

        // game_board 구멍(0) 도형 매칭
        val holes = extractComponents(game_board, 0)
        var filled = 0
        for (h in holes) {
            val hk = canonicalKey(h)
            val cnt = blockCount[hk] ?: 0
            if (cnt > 0) {
                filled += h.size
                if (cnt == 1) blockCount.remove(hk) else blockCount[hk] = cnt - 1
            }
        }

        return filled
    }

    // target(0/1) 연결 컴포넌트들을 좌표 리스트로 추출
    private fun extractComponents(grid: Array<IntArray>, target: Int): List<List<Pair<Int, Int>>> {
        val n = grid.size
        val visited = Array(n) { BooleanArray(n) }
        val dr = intArrayOf(1, -1, 0, 0)
        val dc = intArrayOf(0, 0, 1, -1)

        val comps = ArrayList<List<Pair<Int, Int>>>()

        for (r in 0 until n) {
            for (c in 0 until n) {
                if (visited[r][c] || grid[r][c] != target) continue

                visited[r][c] = true
                val q: ArrayDeque<Pair<Int, Int>> = ArrayDeque()
                q.add(Pair(r, c))
                val pts = ArrayList<Pair<Int, Int>>()

                while (q.isNotEmpty()) {
                    val (cr, cc) = q.removeFirst()
                    pts.add(Pair(cr, cc))

                    for (k in 0 until 4) {
                        val nr = cr + dr[k]
                        val nc = cc + dc[k]
                        if (nr !in 0 until n || nc !in 0 until n) continue
                        if (visited[nr][nc] || grid[nr][nc] != target) continue
                        visited[nr][nc] = true
                        q.add(Pair(nr, nc))
                    }
                }
                comps.add(pts)
            }
        }
        return comps
    }

    private fun normalize(pts: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        var minR = Int.MAX_VALUE
        var minC = Int.MAX_VALUE
        for ((r, c) in pts) {
            if (r < minR) minR = r
            if (c < minC) minC = c
        }
        val norm = pts.map { (r, c) -> Pair(r - minR, c - minC) }
            .sortedWith(compareBy<Pair<Int, Int>> { it.first }.thenBy { it.second })
        return norm
    }

    // (r,c) -> (c, -r)
    private fun rotate90(pts: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
        return pts.map { (r, c) -> Pair(c, -r) }
    }

    private fun keyOf(normSorted: List<Pair<Int, Int>>): String {
        // size<=6 이라 문자열 key로 충분
        val sb = StringBuilder()
        for (i in normSorted.indices) {
            val (r, c) = normSorted[i]
            if (i > 0) sb.append(';')
            sb.append(r).append(',').append(c)
        }
        return sb.toString()
    }

    // ✅ 4회전 중 최소 key를 canonical로 사용 (회전 불변)
    private fun canonicalKey(rawPts: List<Pair<Int, Int>>): String {
        var pts = rawPts
        var best: String? = null
        repeat(4) {
            val norm = normalize(pts)
            val k = keyOf(norm)
            if (best == null || k < best!!) best = k

            // 다음 회전은 "정규화된 좌표"를 기준으로 돌려야 안정적
            pts = rotate90(norm)
        }
        return best!!
    }
}