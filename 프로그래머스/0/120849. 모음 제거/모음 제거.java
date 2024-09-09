class Solution {
    public String solution(String my_string) {
        // 모음 문자 정의
        String vowels = "aeiou";
        
        // StringBuilder를 사용하여 모음 제거
        StringBuilder result = new StringBuilder();
        
        // 문자열을 순회하며 모음이 아닌 문자만 추가
        for (char ch : my_string.toCharArray()) {
            if (vowels.indexOf(ch) == -1) {
                result.append(ch);
            }
        }
        
        // 결과 문자열 반환
        return result.toString();
    }
}
