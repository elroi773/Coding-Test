class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long r1 = x, r2 = x; // 가능한 시작 row 범위 [r1, r2]
        long c1 = y, c2 = y; // 가능한 시작 col 범위 [c1, c2]

        for (int i = queries.length - 1; i >= 0; i--) {
            int cmd = queries[i][0];
            long dx = queries[i][1];

            if (cmd == 0) { // (정방향) 왼쪽 -> (역방향) 오른쪽
                if (c1 != 0) c1 += dx;
                c2 = Math.min((long) m - 1, c2 + dx);

            } else if (cmd == 1) { // (정방향) 오른쪽 -> (역방향) 왼쪽
                c1 = Math.max(0, c1 - dx);
                if (c2 != (long) m - 1) c2 -= dx;

            } else if (cmd == 2) { // (정방향) 위 -> (역방향) 아래
                if (r1 != 0) r1 += dx;
                r2 = Math.min((long) n - 1, r2 + dx);

            } else { // cmd == 3 (정방향) 아래 -> (역방향) 위
                r1 = Math.max(0, r1 - dx);
                if (r2 != (long) n - 1) r2 -= dx;
            }

            // 범위가 비면 불가능
            if (r1 > r2 || c1 > c2) return 0;
        }

        return (r2 - r1 + 1) * (c2 - c1 + 1);
    }
}