class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for (int i = 0; i < balls.length; i++) {
            int a = balls[i][0];
            int b = balls[i][1];
            int minDist = Integer.MAX_VALUE;

            // 1️⃣ 위쪽 벽 반사 (y → 2n - b)
            if (!(startX == a && startY < b)) {
                int y = 2 * n - b;
                int dist = distSq(startX, startY, a, y);
                minDist = Math.min(minDist, dist);
            }

            // 2️⃣ 아래쪽 벽 반사 (y → -b)
            if (!(startX == a && startY > b)) {
                int y = -b;
                int dist = distSq(startX, startY, a, y);
                minDist = Math.min(minDist, dist);
            }

            // 3️⃣ 왼쪽 벽 반사 (x → -a)
            if (!(startY == b && startX > a)) {
                int x = -a;
                int dist = distSq(startX, startY, x, b);
                minDist = Math.min(minDist, dist);
            }

            // 4️⃣ 오른쪽 벽 반사 (x → 2m - a)
            if (!(startY == b && startX < a)) {
                int x = 2 * m - a;
                int dist = distSq(startX, startY, x, b);
                minDist = Math.min(minDist, dist);
            }

            answer[i] = minDist;
        }

        return answer;
    }

    // 거리의 제곱 계산 함수
    private int distSq(int x1, int y1, int x2, int y2) {
        int dx = x1 - x2;
        int dy = y1 - y2;
        return dx * dx + dy * dy;
    }
}
