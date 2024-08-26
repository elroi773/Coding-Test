class Solution {
    public int solution(int n) {
        int count = 0;
        
        // 1부터 n의 제곱근까지 반복
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++; // (i, n/i) 순서쌍
                if (i != n / i) {
                    count++; // (n/i, i) 순서쌍이 다를 때만 추가
                }
            }
        }
        
        return count;
    }
}
