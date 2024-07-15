class Solution {
    public int solution(int num, int n) {
        // num이 n의 배수인지 확인하여, 맞으면 1을 반환하고 그렇지 않으면 0을 반환합니다.
        if (num % n == 0) {
            return 1;
        } else {
            return 0;
        }
    }

    // 메인 메서드 예시 테스트용
    public static void main(String[] args) {
        Solution sol = new Solution();

        // 예제 1
        int num1 = 98;
        int n1 = 2;
        System.out.println(sol.solution(num1, n1)); // 출력: 1

        // 예제 2
        int num2 = 34;
        int n2 = 3;
        System.out.println(sol.solution(num2, n2)); // 출력: 0
    }
}
