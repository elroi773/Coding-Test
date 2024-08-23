class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // n이 0이 될 때까지 반복
        while (n > 0) {
            // n의 마지막 자리 숫자를 더함
            answer += n % 10;
            // n을 10으로 나누어 마지막 자리를 제거
            n /= 10;
        }
        
        return answer;
    }
}
