class Solution {
    fun solution(k: Int, tangerine: IntArray): Int {
        // 1️⃣ 크기별 개수 세기
        val countMap = mutableMapOf<Int, Int>()
        for (t in tangerine) {
            countMap[t] = countMap.getOrDefault(t, 0) + 1
        }

        // 2️⃣ 개수만 추출해서 내림차순 정렬
        val sortedCounts = countMap.values.sortedDescending()

        // 3️⃣ 큰 개수부터 더하면서 k개 채우기
        var total = 0
        var answer = 0
        for (c in sortedCounts) {
            total += c
            answer++
            if (total >= k) break
        }

        return answer
    }
}
