class Solution {
    public String solution(String s, int n) {
        StringBuilder answer = new StringBuilder();  // 문자열을 효율적으로 처리하기 위해 StringBuilder 사용
        
        for (char ch : s.toCharArray()) {  // 문자열의 각 문자를 순회
            if (ch >= 'A' && ch <= 'Z') {  // 대문자일 경우
                answer.append((char) ((ch - 'A' + n) % 26 + 'A'));
            } else if (ch >= 'a' && ch <= 'z') {  // 소문자일 경우
                answer.append((char) ((ch - 'a' + n) % 26 + 'a'));
            } else {  // 공백일 경우
                answer.append(ch);
            }
        }
        
        return answer.toString();  // StringBuilder를 문자열로 변환하여 반환
    }
}
