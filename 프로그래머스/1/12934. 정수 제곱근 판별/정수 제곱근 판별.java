class Solution {
    public long solution(long n) {
        // n의 제곱근을 계산하여 long 타입으로 저장
        long x = (long) Math.sqrt(n);
        
        // x의 제곱이 n과 같은지 확인
        if (x * x == n) {
            // n이 x의 제곱이라면 (x + 1)의 제곱을 계산하여 반환
            return (x + 1) * (x + 1);
        } else {
            // n이 x의 제곱이 아니라면 -1 반환
            return -1;
        }
    }
}
