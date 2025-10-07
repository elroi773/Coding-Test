import java.util.*;

class Solution {
    public int solution(String[] maps) {
        int n = maps.length;
        int m = maps[0].length();

        int[] start = new int[2];
        int[] lever = new int[2];
        int[] exit = new int[2];

        // S, L, E 위치 찾기
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char c = maps[i].charAt(j);
                if (c == 'S') {
                    start[0] = i;
                    start[1] = j;
                } else if (c == 'L') {
                    lever[0] = i;
                    lever[1] = j;
                } else if (c == 'E') {
                    exit[0] = i;
                    exit[1] = j;
                }
            }
        }

        // BFS 함수: 시작점에서 목표까지 최단 거리 반환, 불가능 시 -1
        int distToLever = bfs(maps, start, lever);
        if (distToLever == -1) return -1;

        int distToExit = bfs(maps, lever, exit);
        if (distToExit == -1) return -1;

        return distToLever + distToExit;
    }

    private int bfs(String[] maps, int[] start, int[] target) {
        int n = maps.length;
        int m = maps[0].length();
        boolean[][] visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        int[][] directions = {{1,0},{-1,0},{0,1},{0,-1}};

        queue.add(new int[]{start[0], start[1], 0});
        visited[start[0]][start[1]] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1], dist = cur[2];

            if (x == target[0] && y == target[1]) return dist;

            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && maps[nx].charAt(ny) != 'X') {
                    visited[nx][ny] = true;
                    queue.add(new int[]{nx, ny, dist + 1});
                }
            }
        }
        return -1;
    }
}
