class Solution {
    fun solution(n: Long): Long {
        // 1. 숫자를 문자열로 변환
        val str = n.toString()
        // 2. 각 자릿수를 내림차순 정렬
        val sorted = str.toCharArray().sortedDescending()
        // 3. 다시 문자열로 합친 뒤 Long으로 변환
        val result = sorted.joinToString("").toLong()
        return result
    }
}
