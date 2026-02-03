class Solution {
    fun solution(gems: Array<String>): IntArray {
        val need = gems.toSet().size              // 전체 보석 종류 수
        val count = HashMap<String, Int>()        // 현재 윈도우 카운트

        var l = 0
        var bestL = 0
        var bestR = gems.size - 1
        var bestLen = bestR - bestL

        for (r in gems.indices) {
            val g = gems[r]
            count[g] = (count[g] ?: 0) + 1

            // 모든 종류를 포함하면 왼쪽을 가능한 한 줄이기
            while (count.size == need && l <= r) {
                val len = r - l
                if (len < bestLen || (len == bestLen && l < bestL)) {
                    bestLen = len
                    bestL = l
                    bestR = r
                }

                val leftGem = gems[l]
                val c = (count[leftGem] ?: 0) - 1
                if (c == 0) count.remove(leftGem) else count[leftGem] = c
                l++
            }
        }

        // 진열대 번호는 1부터 시작
        return intArrayOf(bestL + 1, bestR + 1)
    }
}