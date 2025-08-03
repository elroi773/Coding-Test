class Solution {
    fun solution(X: String, Y: String): String {
        val xCount = IntArray(10)
        val yCount = IntArray(10)

        // X 문자열에서 각 숫자 개수 세기
        for (ch in X) {
            xCount[ch - '0']++
        }

        // Y 문자열에서 각 숫자 개수 세기
        for (ch in Y) {
            yCount[ch - '0']++
        }

        val sb = StringBuilder()

        // 큰 숫자부터 공통으로 있는 만큼 추가
        for (i in 9 downTo 0) {
            val count = minOf(xCount[i], yCount[i])
            repeat(count) {
                sb.append(i)
            }
        }

        // 결과 처리
        val result = sb.toString()
        return when {
            result.isEmpty() -> "-1"
            result[0] == '0' -> "0"  // "000..."인 경우
            else -> result
        }
    }
}
