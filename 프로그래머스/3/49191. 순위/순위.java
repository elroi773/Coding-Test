class Solution {
    public int solution(int n, int[][] results) {
        boolean[][] win = new boolean[n + 1][n + 1]; // win[a][b] = a가 b를 이김

        for (int[] r : results) {
            win[r[0]][r[1]] = true;
        }

        // Floyd-Warshall: a>k and k>b => a>b
        for (int k = 1; k <= n; k++) {
            for (int a = 1; a <= n; a++) {
                if (!win[a][k]) continue;
                for (int b = 1; b <= n; b++) {
                    if (win[k][b]) win[a][b] = true;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int known = 0;
            for (int j = 1; j <= n; j++) {
                if (i == j) continue;
                if (win[i][j] || win[j][i]) known++;
            }
            if (known == n - 1) answer++;
        }

        return answer;
    }
}