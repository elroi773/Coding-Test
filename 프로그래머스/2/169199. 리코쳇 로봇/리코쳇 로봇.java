import java.util.*;

class Solution {
    static class Node {
        int x, y, cnt;
        Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    // 상, 하, 좌, 우
    int[] dx = {1, -1, 0, 0};
    int[] dy = {0, 0, 1, -1};

    public int solution(String[] board) {
        int n = board.length;
        int m = board[0].length();

        boolean[][] visited = new boolean[n][m];

        int startX = 0, startY = 0;
        int goalX = 0, goalY = 0;

        // 시작점(R), 목표점(G) 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = board[i].charAt(j);
                if (c == 'R') {
                    startX = i;
                    startY = j;
                }
                if (c == 'G') {
                    goalX = i;
                    goalY = j;
                }
            }
        }

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(startX, startY, 0));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node cur = q.poll();

            // 목표 도착
            if (cur.x == goalX && cur.y == goalY) {
                return cur.cnt;
            }

            // 4방향 이동
            for (int d = 0; d < 4; d++) {
                int nx = cur.x;
                int ny = cur.y;

                // 한 방향으로 끝까지 미끄러짐
                while (true) {
                    int tx = nx + dx[d];
                    int ty = ny + dy[d];

                    if (tx < 0 || ty < 0 || tx >= n || ty >= m) break; // 보드 밖
                    if (board[tx].charAt(ty) == 'D') break; // 장애물

                    nx = tx;
                    ny = ty;
                }

                if (!visited[nx][ny]) {
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny, cur.cnt + 1));
                }
            }
        }

        return -1; // 도착 불가
    }
}
