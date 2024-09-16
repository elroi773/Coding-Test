class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 1부터 n까지의 숫자 중 합성수 찾기
        for (int i = 1; i <= n; i++) {
            // 각 숫자의 약수 개수 구하기
            int divisorCount = 0;
            for (int j = 1; j <= i; j++) {
                if (i % j == 0) {
                    divisorCount++;
                }
            }
            // 약수 개수가 3개 이상이면 합성수
            if (divisorCount >= 3) {
                answer++;
            }
        }
        
        return answer;
    }
}
