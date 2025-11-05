import java.util.*;

class Solution {
    public String[] solution(int[][] line) {
        List<long[]> points = new ArrayList<>();
        int n = line.length;

        // 1️⃣ 모든 직선 쌍의 교점 계산
        for (int i = 0; i < n; i++) {
            long A = line[i][0];
            long B = line[i][1];
            long E = line[i][2];

            for (int j = i + 1; j < n; j++) {
                long C = line[j][0];
                long D = line[j][1];
                long F = line[j][2];

                long denom = A * D - B * C; // 분모
                if (denom == 0) continue;   // 평행하거나 일치
                
                double x = (double)(B * F - E * D) / denom;
                double y = (double)(E * C - A * F) / denom;

                // 정수 좌표만 저장
                if (x == Math.floor(x) && y == Math.floor(y)) {
                    points.add(new long[]{(long)x, (long)y});
                }
            }
        }

        // 2️⃣ 최소/최대 좌표 찾기
        long minX = Long.MAX_VALUE, maxX = Long.MIN_VALUE;
        long minY = Long.MAX_VALUE, maxY = Long.MIN_VALUE;

        for (long[] p : points) {
            minX = Math.min(minX, p[0]);
            maxX = Math.max(maxX, p[0]);
            minY = Math.min(minY, p[1]);
            maxY = Math.max(maxY, p[1]);
        }

        int width = (int)(maxX - minX + 1);
        int height = (int)(maxY - minY + 1);

        // 3️⃣ 격자 초기화
        char[][] grid = new char[height][width];
        for (char[] row : grid) Arrays.fill(row, '.');

        // 4️⃣ 별 찍기
        for (long[] p : points) {
            int x = (int)(p[0] - minX);
            int y = (int)(maxY - p[1]); // 위에서부터 내려오므로 반전
            grid[y][x] = '*';
        }

        // 5️⃣ 문자열 배열로 변환
        String[] answer = new String[height];
        for (int i = 0; i < height; i++) {
            answer[i] = new String(grid[i]);
        }

        return answer;
    }
}
