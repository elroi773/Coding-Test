import java.util.*;

class Solution {
    private static final int MAX = 102; // 좌표 1~50 -> 2배하면 2~100, 여유 포함
    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int[][] board = new int[MAX][MAX];

        // 1) 모든 사각형 영역을 채우기 (겹치는 부분도 포함해서 1로)
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int x = x1; x <= x2; x++) {
                for (int y = y1; y <= y2; y++) {
                    board[x][y] = 1;
                }
            }
        }

        // 2) 각 사각형 내부를 지우기 (테두리 제외하고 0으로)
        for (int[] r : rectangle) {
            int x1 = r[0] * 2;
            int y1 = r[1] * 2;
            int x2 = r[2] * 2;
            int y2 = r[3] * 2;

            for (int x = x1 + 1; x <= x2 - 1; x++) {
                for (int y = y1 + 1; y <= y2 - 1; y++) {
                    board[x][y] = 0;
                }
            }
        }

        // 3) BFS: 테두리(1)만 따라 최단거리
        int startX = characterX * 2;
        int startY = characterY * 2;
        int targetX = itemX * 2;
        int targetY = itemY * 2;

        boolean[][] visited = new boolean[MAX][MAX];
        ArrayDeque<int[]> q = new ArrayDeque<>();
        q.add(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == targetX && y == targetY) {
                // 2배 좌표에서의 이동거리 -> 원래 거리로 복원
                return dist / 2;
            }

            for (int dir = 0; dir < 4; dir++) {
                int nx = x + dx[dir];
                int ny = y + dy[dir];

                if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
                if (visited[nx][ny]) continue;
                if (board[nx][ny] != 1) continue; // 테두리만 이동

                visited[nx][ny] = true;
                q.add(new int[]{nx, ny, dist + 1});
            }
        }

        return 0; // 문제 조건상 도달 가능
    }
}