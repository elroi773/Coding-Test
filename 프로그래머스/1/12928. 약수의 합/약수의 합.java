class Solution {
    public int solution(int n) {
        int answer = 0;
        
        // 1부터 n까지 약수를 모두 찾아 더하기
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {  // i가 n의 약수라면
                answer += i;
            }
        }
        
        return answer;  // 모든 약수를 더한 값 반환
    }
}
