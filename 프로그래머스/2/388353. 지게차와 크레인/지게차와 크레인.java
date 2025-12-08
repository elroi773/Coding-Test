import java.util.*;

class Solution {
    public int solution(String[] storage, String[] requests) {
        int n = storage.length;
        int m = storage[0].length();

        // 창고 상태 복사 (빈 칸은 '.' 로 표시)
        char[][] grid = new char[n][m];
        for (int i = 0; i < n; i++) {
            grid[i] = storage[i].toCharArray();
        }

        // 4방향 (상, 하, 좌, 우)
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        for (String req : requests) {
            char target = req.charAt(0);

            // 크레인 요청: 해당 알파벳 모두 제거
            if (req.length() == 2) {
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m; j++) {
                        if (grid[i][j] == target) {
                            grid[i][j] = '.';
                        }
                    }
                }
                continue;
            }

            // 지게차 요청: 외부와 연결된 빈칸을 BFS로 탐색 (확장 격자 사용)
            int H = n + 2;
            int W = m + 2;
            boolean[][] visited = new boolean[H][W];
            Deque<int[]> q = new ArrayDeque<>();

            // (0,0)은 완전 바깥 영역
            visited[0][0] = true;
            q.offer(new int[]{0, 0});

            while (!q.isEmpty()) {
                int[] cur = q.poll();
                int r = cur[0];
                int c = cur[1];

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                    if (visited[nr][nc]) continue;

                    // 실제 창고 범위인지 확인 (1..n, 1..m)
                    if (1 <= nr && nr <= n && 1 <= nc && nc <= m) {
                        // 창고 안에서 빈칸이면 이동 가능
                        if (grid[nr - 1][nc - 1] == '.') {
                            visited[nr][nc] = true;
                            q.offer(new int[]{nr, nc});
                        }
                    } else {
                        // 창고 밖은 항상 이동 가능
                        visited[nr][nc] = true;
                        q.offer(new int[]{nr, nc});
                    }
                }
            }

            // 외부와 연결된 빈칸(또는 외부)에 인접한 target 컨테이너 제거
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] != target) continue;

                    int er = i + 1; // 확장 격자 기준 좌표
                    int ec = j + 1;
                    boolean removable = false;

                    for (int d = 0; d < 4; d++) {
                        int nr = er + dr[d];
                        int nc = ec + dc[d];

                        if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                        if (visited[nr][nc]) {
                            removable = true;
                            break;
                        }
                    }

                    if (removable) {
                        grid[i][j] = '.';
                    }
                }
            }
        }

        // 남은 컨테이너 개수 세기
        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != '.') answer++;
            }
        }

        return answer;
    }
}