class Solution {

    private int[][] rotate90(int[][] a) {
        int n = a.length;
        int[][] r = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                r[j][n - 1 - i] = a[i][j];
            }
        }
        return r;
    }

    private boolean isUnlocked(int[][] board, int pad, int lockSize) {
        for (int i = 0; i < lockSize; i++) {
            for (int j = 0; j < lockSize; j++) {
                if (board[i + pad][j + pad] != 1) return false;
            }
        }
        return true;
    }

    public boolean solution(int[][] key, int[][] lock) {
        int m = key.length;
        int n = lock.length;

        int pad = m - 1;
        int size = n + pad * 2;

        int[][] board = new int[size][size];

        // 중앙에 lock 배치
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i + pad][j + pad] = lock[i][j];
            }
        }

        int[][] curKey = key;

        for (int rot = 0; rot < 4; rot++) {
            for (int x = 0; x <= size - m; x++) {
                for (int y = 0; y <= size - m; y++) {

                    // key를 board에 더하기
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            board[x + i][y + j] += curKey[i][j];
                        }
                    }

                    if (isUnlocked(board, pad, n)) return true;

                    // 원복
                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < m; j++) {
                            board[x + i][y + j] -= curKey[i][j];
                        }
                    }
                }
            }
            curKey = rotate90(curKey);
        }

        return false;
    }
}