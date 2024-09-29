class Solution {
    public int solution(int number, int limit, int power) {
        int totalWeight = 0;
        
        // 1번부터 number까지 각 기사의 번호에 대해 반복
        for (int i = 1; i <= number; i++) {
            int divisorCount = getDivisorCount(i);  // 현재 번호 i의 약수 개수 구하기
            
            // 약수 개수가 limit을 초과하면 power를 사용하고, 그렇지 않으면 약수 개수를 그대로 사용
            if (divisorCount > limit) {
                totalWeight += power;
            } else {
                totalWeight += divisorCount;
            }
        }
        
        return totalWeight;
    }

    // 약수 개수를 구하는 함수
    private int getDivisorCount(int n) {
        int count = 0;
        
        // 1부터 sqrt(n)까지 약수를 확인
        for (int i = 1; i * i <= n; i++) {
            if (n % i == 0) {
                count++;  // i는 약수
                
                // i가 n의 제곱근이 아니라면 n / i도 약수
                if (i != n / i) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
