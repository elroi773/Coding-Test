class Solution {
    public int solution(int n) {
        // 6과 n의 최소공배수를 구하는 함수
        int lcm = lcm(6, n);
        
        // 최소공배수를 6으로 나누면 몇 판의 피자가 필요한지 알 수 있다.
        return lcm / 6;
    }
    
    // 최대공약수를 구하는 함수 (유클리드 호제법 사용)
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
    
    // 최소공배수를 구하는 함수
    private int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }
}
