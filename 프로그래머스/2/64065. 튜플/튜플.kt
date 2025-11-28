class Solution {
    fun solution(s: String): IntArray {
        // 1. 바깥 {{ }} 제거
        val trimmed = s.substring(2, s.length - 2)

        // 2. "},{" 기준으로 나누기
        val parts = trimmed.split("},{")

        // 3. 각 부분을 숫자 리스트로 변환
        val list = parts.map { part ->
            part.split(",").map { it.toInt() }
        }

        // 4. 원소 개수 기준 오름차순 정렬
        val sorted = list.sortedBy { it.size }

        val answer = mutableListOf<Int>()
        val seen = HashSet<Int>()

        // 5. 작은 집합부터 새로운 숫자만 추가
        for (subset in sorted) {
            for (num in subset) {
                if (!seen.contains(num)) {
                    seen.add(num)
                    answer.add(num)
                }
            }
        }

        return answer.toIntArray()
    }
}
