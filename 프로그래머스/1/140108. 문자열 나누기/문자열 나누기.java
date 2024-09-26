class Solution {
    public int solution(String s) {
        int answer = 0;  // 분리된 문자열의 개수를 저장하는 변수
        int countX = 0;  // 첫 글자와 같은 글자의 개수
        int countOther = 0;  // 첫 글자와 다른 글자의 개수
        
        char x = s.charAt(0);  // 첫 글자를 x로 설정
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == x) {
                countX++;  // 첫 글자와 같은 경우
            } else {
                countOther++;  // 첫 글자와 다른 경우
            }
            
            // 두 횟수가 같아지면 문자열을 분리
            if (countX == countOther) {
                answer++;
                // 다시 새로운 분리를 위해 다음 문자를 기준으로 초기화
                if (i + 1 < s.length()) {
                    x = s.charAt(i + 1);  // 새로운 첫 글자 설정
                }
                countX = 0;
                countOther = 0;
            }
        }
        
        // 남은 문자열이 있을 경우 마지막 부분 처리
        if (countX != 0 || countOther != 0) {
            answer++;
        }
        
        return answer;
    }
}
