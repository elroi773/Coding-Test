class Solution {
    public String solution(int n) {
        StringBuilder answer = new StringBuilder(); // StringBuilder 사용하여 효율적인 문자열 생성
        
        // n이 0이면 빈 문자열을 반환
        if (n == 0) {
            return "";
        }
        
        for (int i = 0; i < n; i++) {
            // 홀수 인덱스는 "수", 짝수 인덱스는 "박"을 추가
            if (i % 2 == 0) {
                answer.append("수");
            } else {
                answer.append("박");
            }
        }
        
        return answer.toString(); // StringBuilder를 String으로 변환하여 반환
    }
}
