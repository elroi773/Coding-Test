import java.util.*;

class Solution {
    public int solution(int[][] land) {
        int n = land.length;        // 세로
        int m = land[0].length;     // 가로

        boolean[][] visited = new boolean[n][m];
        int[] colTotal = new int[m];    // 각 열마다 얻을 수 있는 석유량

        // 상하좌우
        int[] dr = {1, -1, 0, 0};
        int[] dc = {0, 0, 1, -1};

        int answer = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                // 아직 방문하지 않은 석유(1) 칸이면 새로운 덩어리 탐색 시작
                if (land[i][j] == 1 && !visited[i][j]) {
                    // DFS 또는 BFS (여기서는 스택을 이용한 DFS)
                    Deque<int[]> stack = new ArrayDeque<>();
                    stack.push(new int[]{i, j});
                    visited[i][j] = true;

                    int size = 0;                 // 이 석유 덩어리의 전체 크기
                    boolean[] colUsed = new boolean[m];
                    List<Integer> cols = new ArrayList<>(); // 이 덩어리가 차지하는 열 목록

                    while (!stack.isEmpty()) {
                        int[] cur = stack.pop();
                        int r = cur[0];
                        int c = cur[1];

                        size++;

                        // 이 열을 처음 방문하는 경우만 기록
                        if (!colUsed[c]) {
                            colUsed[c] = true;
                            cols.add(c);
                        }

                        // 인접 4방향 탐색
                        for (int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];

                            if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                            if (land[nr][nc] == 1 && !visited[nr][nc]) {
                                visited[nr][nc] = true;
                                stack.push(new int[]{nr, nc});
                            }
                        }
                    }

                    // 이 덩어리가 지나가는 각 열마다 덩어리 크기를 더해줌
                    for (int col : cols) {
                        colTotal[col] += size;
                        if (colTotal[col] > answer) {
                            answer = colTotal[col];
                        }
                    }
                }
            }
        }

        return answer;
    }
}