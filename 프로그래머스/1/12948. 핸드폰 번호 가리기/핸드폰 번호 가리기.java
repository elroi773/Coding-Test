class Solution {
    public String solution(String phone_number) {
        int length = phone_number.length();  // 전화번호의 길이를 구함
        String hiddenPart = "*".repeat(length - 4);  // 앞부분을 '*'로 가림
        String visiblePart = phone_number.substring(length - 4);  // 뒷 4자리 추출
        
        return hiddenPart + visiblePart;  // 앞부분 *와 뒷 4자리를 합침
    }
}
