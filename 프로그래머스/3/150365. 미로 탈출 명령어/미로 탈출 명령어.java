class Solution {
    private int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        int d0 = dist(x, y, r, c);
        if (d0 > k || ((k - d0) & 1) == 1) return "impossible";

        StringBuilder sb = new StringBuilder(k);
        int cx = x, cy = y;

        // 사전순(ASCII): d < l < r < u
        char[] dc = {'d', 'l', 'r', 'u'};
        int[] dx = { 1,  0,  0, -1};
        int[] dy = { 0, -1,  1,  0};

        for (int step = 0; step < k; step++) {
            boolean moved = false;

            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];

                // 범위 체크
                if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

                int rem = k - (step + 1);
                int nd = dist(nx, ny, r, c);

                // 남은 횟수로 도착 가능 + parity(홀짝) 맞음
                if (nd <= rem && ((rem - nd) & 1) == 0) {
                    sb.append(dc[i]);
                    cx = nx; cy = ny;
                    moved = true;
                    break;
                }
            }

            // 안전장치 (원칙상 발생 X)
            if (!moved) return "impossible";
        }

        return sb.toString();
    }
}