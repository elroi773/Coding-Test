class Solution {
    public String solution(String cipher, int code) {
        StringBuilder answer = new StringBuilder();
        
        // code의 배수 번째 문자만 추출
        for (int i = code - 1; i < cipher.length(); i += code) {
            answer.append(cipher.charAt(i));
        }
        
        return answer.toString();
    }
}
