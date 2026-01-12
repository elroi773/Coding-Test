import java.util.*;

class Solution {
    public int solution(int[][] beginning, int[][] target) {
        int R = beginning.length;
        int C = beginning[0].length;

        // diff = beginning XOR target
        int[][] diff = new int[R][C];
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                diff[i][j] = beginning[i][j] ^ target[i][j];
            }
        }

        int best = Integer.MAX_VALUE;
        int totalMasks = 1 << R;

        for (int mask = 0; mask < totalMasks; mask++) {
            // colFlip[j]는 0행을 0으로 만들도록 자동 결정
            int[] colFlip = new int[C];
            int rf0 = (mask & 1); // rowFlip(0)
            int colCount = 0;

            for (int j = 0; j < C; j++) {
                colFlip[j] = diff[0][j] ^ rf0;
                colCount += colFlip[j];
            }

            // 전체 검증: diff[i][j] ^ rowFlip(i) ^ colFlip(j) == 0
            boolean ok = true;
            for (int i = 0; i < R && ok; i++) {
                int rfi = (mask >> i) & 1;
                for (int j = 0; j < C; j++) {
                    if ((diff[i][j] ^ rfi ^ colFlip[j]) != 0) {
                        ok = false;
                        break;
                    }
                }
            }

            if (ok) {
                int rowCount = Integer.bitCount(mask);
                best = Math.min(best, rowCount + colCount);
            }
        }

        return best == Integer.MAX_VALUE ? -1 : best;
    }
}