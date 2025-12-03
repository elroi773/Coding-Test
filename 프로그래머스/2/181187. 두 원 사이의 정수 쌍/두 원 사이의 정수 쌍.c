#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>

long long solution(int r1, int r2) {
    long long answer = 0;

    long long R1 = (long long)r1;
    long long R2 = (long long)r2;
    long long R1sq = R1 * R1;
    long long R2sq = R2 * R2;

    for (int x = 0; x <= r2; x++) {
        long long xx = (long long)x * x;

        // 바깥 원에서의 최대 y
        long long maxY = (long long)floor(sqrt((double)(R2sq - xx)));

        // 안쪽 원에서의 최소 y
        long long minY;
        long long diff1 = R1sq - xx;

        if (diff1 > 0) {
            minY = (long long)ceil(sqrt((double)diff1));
        } else {
            minY = 0;
        }

        if (maxY < minY) continue;  // 해당 x에서 유효한 y 없음

        if (x == 0) {
            // y축 위: (0, y), (0, -y) → 각 y당 2개
            answer += 2 * (maxY - minY + 1);
        } else {
            if (minY == 0) {
                // y = 0: (±x, 0) → 2개
                answer += 2;
                // y >= 1: (±x, ±y) → y 하나당 4개
                answer += 4 * maxY;
            } else {
                // y >= 1: (±x, ±y) → y 하나당 4개
                answer += 4 * (maxY - minY + 1);
            }
        }
    }

    return answer;
}