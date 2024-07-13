class Solution {
    public int solution(int number, int n, int m) {
        // number가 n의 배수이고 m의 배수인 경우
        if (number % n == 0 && number % m == 0) {
            return 1;
        }
        // 그렇지 않은 경우
        return 0;
    }
}
