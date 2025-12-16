class Solution {
    public String solution(int n, int t, int m, int p) {
        StringBuilder seq = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        
        int num = 0;

        // 충분한 길이의 N진수 문자열 만들기
        while (seq.length() < t * m) {
            seq.append(Integer.toString(num, n).toUpperCase());
            num++;
        }

        // 튜브가 말해야 할 숫자만 추출
        for (int i = p - 1; answer.length() < t; i += m) {
            answer.append(seq.charAt(i));
        }

        return answer.toString();
    }
}
