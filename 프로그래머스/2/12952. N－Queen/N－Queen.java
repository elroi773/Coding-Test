class Solution {
    int answer = 0;
    boolean[] col;
    boolean[] diag1;
    boolean[] diag2;

    public int solution(int n) {
        col = new boolean[n];
        diag1 = new boolean[2 * n];
        diag2 = new boolean[2 * n];

        backtrack(0, n);
        return answer;
    }

    void backtrack(int row, int n) {
        if (row == n) {
            answer++;
            return;
        }

        for (int c = 0; c < n; c++) {
            if (col[c] || diag1[row - c + n] || diag2[row + c]) {
                continue;
            }

            // 퀸 배치
            col[c] = diag1[row - c + n] = diag2[row + c] = true;

            backtrack(row + 1, n);

            // 백트래킹
            col[c] = diag1[row - c + n] = diag2[row + c] = false;
        }
    }
}
