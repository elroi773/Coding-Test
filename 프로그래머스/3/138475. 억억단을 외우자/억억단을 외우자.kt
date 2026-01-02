class Solution {
    fun solution(e: Int, starts: IntArray): IntArray {
        // 1) 약수 개수
        val cnt = IntArray(e + 1)
        for (i in 1..e) {
            var j = i
            while (j <= e) {
                cnt[j]++
                j += i
            }
        }

        // 2) best[i]
        val best = IntArray(e + 1)
        var bestNum = e
        var bestCnt = cnt[e]
        best[e] = e

        for (i in e - 1 downTo 1) {
            if (cnt[i] >= bestCnt) { // 동점이면 작은 i
                bestCnt = cnt[i]
                bestNum = i
            }
            best[i] = bestNum
        }

        // 3) 질의
        val ans = IntArray(starts.size)
        for (idx in starts.indices) {
            ans[idx] = best[starts[idx]]
        }
        return ans
    }
}