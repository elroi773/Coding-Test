class Solution {
    fun solution(myString: String): IntArray {
        // "x"를 기준으로 문자열 나누기
        val parts = myString.split('x')
        
        // 나누어진 부분들의 길이를 배열로 저장
        return parts.map { it.length }.toIntArray()
    }
}
