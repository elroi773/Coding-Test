class Solution {
    fun solution(phone_number: String): String {
        val length = phone_number.length
        val hidden = "*".repeat(length - 4)       // 앞부분 별 처리
        val visible = phone_number.takeLast(4)    // 뒤 4자리 추출
        return hidden + visible
    }
}
