class Solution {
    public int solution(String my_string) {
        int answer = 0;
        
        // 문자열의 각 문자를 확인
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i);
            
            // 문자가 숫자인지 확인
            if (Character.isDigit(ch)) {
                // 숫자를 정수로 변환 후 더하기
                answer += ch - '0';  // '0'을 빼면 문자형 숫자가 실제 숫자로 변환됨
            }
        }
        
        return answer;
    }
}
