class Solution {
    public int solution(int[] nums) {
        int answer = 0;
        
        // 3개의 수를 더하는 모든 경우의 수를 확인
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    
                    // 더한 값이 소수인지 확인
                    if (isPrime(sum)) {
                        answer++;
                    }
                }
            }
        }
        
        return answer;
    }
    
    // 소수 판별 함수
    public boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
