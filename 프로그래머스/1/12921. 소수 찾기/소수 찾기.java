class Solution {
    public int solution(int n) {
        // 소수 여부를 저장하는 배열 생성
        boolean[] isPrime = new boolean[n + 1];
        
        // 모든 숫자를 일단 소수로 간주
        for (int i = 2; i <= n; i++) {
            isPrime[i] = true;
        }
        
        // 에라토스테네스의 체 적용
        for (int i = 2; i * i <= n; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    isPrime[j] = false;  // i의 배수는 소수가 아님
                }
            }
        }
        
        // 소수의 개수 세기
        int count = 0;
        for (int i = 2; i <= n; i++) {
            if (isPrime[i]) {
                count++;
            }
        }
        
        return count;
    }
}
