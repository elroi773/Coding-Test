class Solution {
    public long solution(int r1, int r2) {
        long answer = 0;

        long R1 = (long) r1;
        long R2 = (long) r2;
        long R1sq = R1 * R1;
        long R2sq = R2 * R2;

        for (int x = 0; x <= r2; x++) {
            long xx = (long) x * x;

            // 바깥 원에서의 최대 y
            long maxY = (long) Math.floor(Math.sqrt(R2sq - xx));

            // 안쪽 원에서의 최소 y
            long minY;
            long diff1 = R1sq - xx;
            if (diff1 > 0) {
                minY = (long) Math.ceil(Math.sqrt(diff1));
            } else {
                minY = 0;
            }

            if (maxY < minY) continue; // 이 x에서는 유효한 점 없음

            if (x == 0) {
                // y축 위: (0, y), (0, -y) → 각 y마다 2개
                answer += 2L * (maxY - minY + 1);
            } else {
                if (minY == 0) {
                    // y = 0: (±x, 0) → 2개
                    answer += 2L;
                    // y >= 1: (±x, ±y) → y 하나당 4개
                    answer += 4L * maxY;
                } else {
                    // y >= minY: (±x, ±y) → y 하나당 4개
                    answer += 4L * (maxY - minY + 1);
                }
            }
        }

        return answer;
    }
}