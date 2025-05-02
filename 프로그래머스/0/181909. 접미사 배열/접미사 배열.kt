class Solution {
    fun solution(my_string: String): Array<String> {
        // 접미사 배열 생성
        val suffixes = Array(my_string.length) { i -> my_string.substring(i) }

        // 사전순 정렬
        suffixes.sort()

        return suffixes
    }
}
