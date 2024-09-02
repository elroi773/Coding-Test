class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        // 두 분모의 최소공배수를 구합니다.
        int lcm = denom1 * denom2 / gcd(denom1, denom2);
        
        // 첫 번째 분수와 두 번째 분수를 공통 분모로 변환하여 더합니다.
        int newNumer = (numer1 * (lcm / denom1)) + (numer2 * (lcm / denom2));
        int newDenom = lcm;
        
        // 더한 결과의 분자와 분모의 최대공약수를 구합니다.
        int gcdValue = gcd(newNumer, newDenom);
        
        // 기약 분수로 변환합니다.
        int[] answer = { newNumer / gcdValue, newDenom / gcdValue };
        
        return answer;
    }

    // 최대공약수를 구하는 함수 (유클리드 호제법)
    private int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
