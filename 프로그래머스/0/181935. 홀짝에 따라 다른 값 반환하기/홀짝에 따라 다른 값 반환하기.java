class Solution {
    public int solution(int n) {
        int sum = 0;

        if (n % 2 != 0) {
            // n이 홀수일 때, n 이하의 홀수들의 합을 계산
            for (int i = 1; i <= n; i += 2) {
                sum += i;
            }
        } else {
            // n이 짝수일 때, n 이하의 짝수들의 제곱합을 계산
            for (int i = 2; i <= n; i += 2) {
                sum += i * i;
            }
        }

        return sum;
    }

    // 메인 메서드 예시 테스트용
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 예제 1
        int n1 = 7;
        System.out.println(sol.solution(n1)); // 출력: 16

        // 예제 2
        int n2 = 10;
        System.out.println(sol.solution(n2)); // 출력: 220
    }
}
