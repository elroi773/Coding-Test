class Solution {
    public long solution(int w, int h) {
        long W = w;
        long H = h;

        long g = gcd(W, H);
        // 전체 정사각형 개수 - (대각선이 지나가서 못 쓰는 정사각형 개수)
        // 못 쓰는 개수 = W + H - gcd(W, H)
        return W * H - (W + H - g);
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long r = a % b;
            a = b;
            b = r;
        }
        return a;
    }
}