class Solution {
    public int solution(int n, int t) {
        // 세균의 수는 2의 t제곱만큼 증가
        return n * (int) Math.pow(2, t);
    }
}
