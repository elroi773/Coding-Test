class Solution {
    public int solution(int n) {
        // 1. n의 제곱근을 구함
        int sqrt = (int) Math.sqrt(n);
        
        // 2. 제곱근의 제곱이 n과 같은지 확인
        if (sqrt * sqrt == n) {
            return 1; // 제곱수인 경우
        } else {
            return 2; // 제곱수가 아닌 경우
        }
    }
}
