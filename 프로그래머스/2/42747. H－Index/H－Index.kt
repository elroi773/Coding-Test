class Solution {
    fun solution(citations: IntArray): Int {
        citations.sort()  // 오름차순 정렬
        val n = citations.size

        for (i in citations.indices) {
            val h = n - i               // 남은 논문 수 = 후보 h값
            if (citations[i] >= h) {    // 조건 만족하면 바로 H-index
                return h
            }
        }

        return 0
    }
}
