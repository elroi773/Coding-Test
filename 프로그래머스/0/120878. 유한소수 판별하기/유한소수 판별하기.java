class Solution {
    public int solution(int a, int b) {
        // a와 b의 최대공약수를 구함
        int gcd = gcd(a, b);
        
        // 분모를 최대공약수로 나눠 기약분수로 변환
        b /= gcd;
        
        // 분모에서 2를 최대한 나눔
        while (b % 2 == 0) {
            b /= 2;
        }
        
        // 분모에서 5를 최대한 나눔
        while (b % 5 == 0) {
            b /= 5;
        }
        
        // 분모가 1이 되면 유한소수, 그렇지 않으면 무한소수
        return b == 1 ? 1 : 2;
    }
    
    // 최대공약수를 구하는 함수 (유클리드 호제법)
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
