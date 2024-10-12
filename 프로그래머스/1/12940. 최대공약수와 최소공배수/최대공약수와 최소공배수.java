class Solution {
    // 유클리드 호제법으로 최대공약수 구하는 함수
    public int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int[] solution(int n, int m) {
        int gcd = gcd(n, m);  // 최대공약수
        int lcm = (n * m) / gcd;  // 최소공배수

        // 결과 배열에 최대공약수와 최소공배수를 담아 반환
        return new int[] {gcd, lcm};
    }
}
