import java.util.*;

class Solution {
    // 방향: 상, 우, 하, 좌
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] grid) {
        int n = grid.length;
        int m = grid[0].length();
        
        boolean[][][] visited = new boolean[n][m][4];
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                for (int d = 0; d < 4; d++) {
                    if (!visited[i][j][d]) {
                        int x = i, y = j, dir = d;
                        int count = 0;

                        while (!visited[x][y][dir]) {
                            visited[x][y][dir] = true;
                            count++;

                            // 다음 위치 (토러스 구조)
                            x = (x + dx[dir] + n) % n;
                            y = (y + dy[dir] + m) % m;

                            // 다음 칸에서 방향 전환
                            char c = grid[x].charAt(y);
                            if (c == 'L') dir = (dir + 3) % 4; // 왼쪽 회전
                            else if (c == 'R') dir = (dir + 1) % 4; // 오른쪽 회전
                        }

                        result.add(count);
                    }
                }
            }
        }

        // 오름차순 정렬
        Collections.sort(result);

        // List → int[] 변환
        return result.stream().mapToInt(i -> i).toArray();
    }
}
