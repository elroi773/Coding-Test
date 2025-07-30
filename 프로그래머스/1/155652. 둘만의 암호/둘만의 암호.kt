class Solution {
    fun solution(s: String, skip: String, index: Int): String {
        // 1. 유효한 알파벳 리스트 생성 (skip 제외)
        val valid = ('a'..'z').filter { it !in skip }

        // 2. 변환 처리
        return s.map { ch ->
            val currentIndex = valid.indexOf(ch)
            val newIndex = (currentIndex + index) % valid.size
            valid[newIndex]
        }.joinToString("")
    }
}
