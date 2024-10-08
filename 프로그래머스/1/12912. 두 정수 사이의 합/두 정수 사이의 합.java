class Solution {
    public long solution(int a, int b) {
        // a와 b 중 작은 값과 큰 값을 구함
        int start = Math.min(a, b);
        int end = Math.max(a, b);
        
        // start부터 end까지 모든 수의 합을 계산
        long sum = 0;
        for (int i = start; i <= end; i++) {
            sum += i;
        }
        
        return sum;
    }
}
