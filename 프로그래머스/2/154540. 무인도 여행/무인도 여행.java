import java.util.*;

class Solution {
    public int[] solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        List<Integer> islandList = new ArrayList<>();

        // 상하좌우 방향 정의
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        // BFS 탐색
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (!visited[i][j] && maps[i].charAt(j) != 'X') {
                    int totalFood = 0;
                    Queue<int[]> q = new LinkedList<>();
                    q.add(new int[]{i, j});
                    visited[i][j] = true;

                    while (!q.isEmpty()) {
                        int[] cur = q.poll();
                        int x = cur[0];
                        int y = cur[1];
                        totalFood += maps[x].charAt(y) - '0'; // 숫자 변환

                        for (int dir = 0; dir < 4; dir++) {
                            int nx = x + dx[dir];
                            int ny = y + dy[dir];

                            // 범위 확인 + 방문 여부 + 바다(X) 확인
                            if (nx >= 0 && nx < n && ny >= 0 && ny < m
                                    && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                                visited[nx][ny] = true;
                                q.add(new int[]{nx, ny});
                            }
                        }
                    }

                    islandList.add(totalFood);
                }
            }
        }

        if (islandList.isEmpty()) return new int[]{-1};

        Collections.sort(islandList);
        int[] answer = new int[islandList.size()];
        for (int i = 0; i < islandList.size(); i++) {
            answer[i] = islandList.get(i);
        }

        return answer;
    }
}
