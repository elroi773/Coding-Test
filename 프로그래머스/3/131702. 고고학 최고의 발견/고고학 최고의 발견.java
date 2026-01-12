import java.util.*;

class Solution {
    private int n;

    private void press(int[][] b, int r, int c, int t) {
        if (t == 0) return;
        b[r][c] = (b[r][c] + t) & 3;
        if (r > 0) b[r - 1][c] = (b[r - 1][c] + t) & 3;
        if (r < n - 1) b[r + 1][c] = (b[r + 1][c] + t) & 3;
        if (c > 0) b[r][c - 1] = (b[r][c - 1] + t) & 3;
        if (c < n - 1) b[r][c + 1] = (b[r][c + 1] + t) & 3;
    }

    public int solution(int[][] clockHands) {
        n = clockHands.length;
        int best = Integer.MAX_VALUE;

        // 4^n = 2^(2n): 첫 행 각 칸(0~3회)을 2비트로 표현
        int cases = 1 << (2 * n);

        for (int mask = 0; mask < cases; mask++) {
            // board 복사
            int[][] b = new int[n][n];
            for (int i = 0; i < n; i++) {
                System.arraycopy(clockHands[i], 0, b[i], 0, n);
            }

            int cnt = 0;
            int tmp = mask;

            // 1) 첫 행 조작 적용
            for (int c = 0; c < n; c++) {
                int t = tmp & 3;  // 0~3
                tmp >>= 2;
                cnt += t;
                press(b, 0, c, t);
            }

            // 2) 2행~n행: 윗칸을 0으로 만들기 위한 조작은 강제
            for (int r = 1; r < n; r++) {
                for (int c = 0; c < n; c++) {
                    int above = b[r - 1][c];
                    if (above != 0) {
                        int t = (4 - above) & 3; // 1~3
                        cnt += t;
                        press(b, r, c, t);
                    }
                }
            }

            // 3) 마지막 행이 모두 0이면 성공
            boolean ok = true;
            for (int c = 0; c < n; c++) {
                if (b[n - 1][c] != 0) {
                    ok = false;
                    break;
                }
            }

            if (ok) best = Math.min(best, cnt);
        }

        return best;
    }
}