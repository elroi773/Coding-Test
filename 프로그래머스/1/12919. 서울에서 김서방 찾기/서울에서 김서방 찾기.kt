class Solution {
    fun solution(seoul: Array<String>): String {
        // "Kim"의 위치 찾기
        val idx = seoul.indexOf("Kim")
        
        // 원하는 문자열 반환
        return "김서방은 ${idx}에 있다"
    }
}
