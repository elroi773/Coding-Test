public class Solution {
    public int solution(int n) {
        int answer = 0;

        // n이 0이 될 때까지 반복하면서 자릿수를 더함
        while (n > 0) {
            answer += n % 10;  // 마지막 자릿수를 더함
            n /= 10;  // n을 10으로 나누어 자릿수를 줄임
        }

        return answer;
    }
}
