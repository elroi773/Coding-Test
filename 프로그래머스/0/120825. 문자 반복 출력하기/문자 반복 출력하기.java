class Solution {
    public String solution(String my_string, int n) {
        StringBuilder answer = new StringBuilder(); // 문자열을 효율적으로 연결하기 위해 StringBuilder 사용

        // 문자열의 각 문자를 n번 반복하여 answer에 추가
        for (int i = 0; i < my_string.length(); i++) {
            char ch = my_string.charAt(i); // my_string의 i번째 문자
            for (int j = 0; j < n; j++) {
                answer.append(ch); // 문자를 n번 추가
            }
        }
        
        return answer.toString(); // StringBuilder를 문자열로 변환하여 반환
    }
}
