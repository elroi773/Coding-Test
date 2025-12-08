class Solution {
    public int[] solution(int n) {
        int total = n * (n + 1) / 2;   // 채워야 하는 숫자 개수
        int[] answer = new int[total];

        // 달팽이를 채울 임시 2차원 배열
        int[][] tri = new int[n][n];

        // 방향: 아래(0), 오른쪽(1), 왼쪽 위(2)
        int[] dr = {1, 0, -1};
        int[] dc = {0, 1, -1};

        int r = 0, c = 0, dir = 0;

        for (int num = 1; num <= total; num++) {
            tri[r][c] = num;

            int nr = r + dr[dir];
            int nc = c + dc[dir];

            // 범위를 벗어나거나 이미 숫자가 있으면 방향 전환
            if (nr < 0 || nr >= n || nc < 0 || nc >= n || tri[nr][nc] != 0) {
                dir = (dir + 1) % 3;
                nr = r + dr[dir];
                nc = c + dc[dir];
            }

            r = nr;
            c = nc;
        }

        // 삼각형 부분만 차례대로 1차원 배열로 옮기기
        int idx = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                answer[idx++] = tri[i][j];
            }
        }

        return answer;
    }
}