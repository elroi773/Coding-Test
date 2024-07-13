class Solution {
    public int solution(int a, int b) {
        // 정수 a와 b를 문자열로 변환하여 이어붙임
        String abStr = String.valueOf(a) + String.valueOf(b);
        // 문자열을 정수로 변환
        int ab = Integer.parseInt(abStr);
        
        // 2 * a * b를 계산
        int doubleProduct = 2 * a * b;
        
        // ab와 doubleProduct 중 더 큰 값을 반환 (같다면 ab를 반환)
        return Math.max(ab, doubleProduct);
    }
}
