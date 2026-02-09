import kotlin.math.min

class Solution {
    private var n = 0
    private var m = 0
    private lateinit var extended: IntArray
    private lateinit var distSorted: IntArray
    private lateinit var used: BooleanArray
    private lateinit var perm: IntArray
    private var best = Int.MAX_VALUE

    fun solution(n: Int, weak: IntArray, dist: IntArray): Int {
        this.n = n
        this.m = weak.size

        // weak 확장(원형 -> 선형)
        extended = IntArray(m * 2)
        for (i in 0 until m) {
            extended[i] = weak[i]
            extended[i + m] = weak[i] + n
        }

        // dist 정렬(중복 순열 가지치기 도움)
        distSorted = dist.clone()
        distSorted.sort()

        best = Int.MAX_VALUE

        // 시작점을 weak의 각 지점으로 잡고, dist 순열(prefix)로 커버 가능한지 확인
        for (start in 0 until m) {
            val segment = IntArray(m) { idx -> extended[start + idx] }

            used = BooleanArray(distSorted.size)
            perm = IntArray(distSorted.size)

            dfs(0, segment)
        }

        return if (best == Int.MAX_VALUE) -1 else best
    }

    private fun dfs(depth: Int, segment: IntArray) {
        if (depth >= best) return // 가지치기

        // 1명 이상 뽑았으면 지금 prefix로 커버 가능 여부 검사
        if (depth > 0) {
            if (canCover(segment, depth)) {
                best = min(best, depth)
                return
            }
        }

        if (depth == distSorted.size) return

        var prev = -1
        for (i in distSorted.indices) {
            if (used[i]) continue
            if (prev != -1 && distSorted[i] == prev) continue // 중복 제거
            prev = distSorted[i]

            used[i] = true
            perm[depth] = distSorted[i]
            dfs(depth + 1, segment)
            used[i] = false
        }
    }

    // perm[0..count-1] 친구들로 segment(취약점 m개)을 전부 덮을 수 있는지
    private fun canCover(segment: IntArray, count: Int): Boolean {
        var usedCnt = 1
        var coverageEnd = segment[0] + perm[0]

        for (i in 0 until m) {
            if (segment[i] > coverageEnd) {
                usedCnt++
                if (usedCnt > count) return false
                coverageEnd = segment[i] + perm[usedCnt - 1]
            }
        }
        return true
    }
}