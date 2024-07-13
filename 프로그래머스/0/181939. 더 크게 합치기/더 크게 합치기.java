class Solution {
    public int solution(int a, int b) {
        // 정수 a와 b를 문자열로 변환하여 이어붙임
        String abStr = String.valueOf(a) + String.valueOf(b);
        String baStr = String.valueOf(b) + String.valueOf(a);

        // 문자열을 정수로 변환
        int ab = Integer.parseInt(abStr);
        int ba = Integer.parseInt(baStr);

        // ab와 ba 중 더 큰 값을 반환
        return Math.max(ab, ba);
    }
}
