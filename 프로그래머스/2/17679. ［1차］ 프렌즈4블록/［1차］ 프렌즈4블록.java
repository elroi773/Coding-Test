class Solution {
    public int solution(int m, int n, String[] board) {
        int answer = 0;

        // board를 char 배열로 변환
        char[][] map = new char[m][n];
        for (int i = 0; i < m; i++) {
            map[i] = board[i].toCharArray();
        }

        while (true) {
            boolean[][] remove = new boolean[m][n];
            int count = 0;

            // 1️⃣ 2x2 블록 찾기
            for (int i = 0; i < m - 1; i++) {
                for (int j = 0; j < n - 1; j++) {
                    char cur = map[i][j];
                    if (cur == '.') continue;

                    if (map[i][j + 1] == cur &&
                        map[i + 1][j] == cur &&
                        map[i + 1][j + 1] == cur) {

                        remove[i][j] = true;
                        remove[i][j + 1] = true;
                        remove[i + 1][j] = true;
                        remove[i + 1][j + 1] = true;
                    }
                }
            }

            // 2️⃣ 제거
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (remove[i][j]) {
                        map[i][j] = '.';
                        count++;
                    }
                }
            }

            // 더 이상 제거할 블록이 없으면 종료
            if (count == 0) break;
            answer += count;

            // 3️⃣ 중력 적용
            for (int j = 0; j < n; j++) {
                int empty = m - 1;
                for (int i = m - 1; i >= 0; i--) {
                    if (map[i][j] != '.') {
                        map[empty][j] = map[i][j];
                        if (empty != i) map[i][j] = '.';
                        empty--;
                    }
                }
            }
        }

        return answer;
    }
}
