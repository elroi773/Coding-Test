#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// queries_rows는 2차원 배열 queries의 행 길이, queries_cols는 2차원 배열 queries의 열 길이입니다.
long long solution(int n, int m, int x, int y, int** queries, size_t queries_rows, size_t queries_cols) {
    (void)queries_cols; // 항상 2라 가정, 경고 방지

    long long r1 = x, r2 = x; // 가능한 시작 row 범위 [r1, r2]
    long long c1 = y, c2 = y; // 가능한 시작 col 범위 [c1, c2]

    for (long long i = (long long)queries_rows - 1; i >= 0; i--) {
        int cmd = queries[i][0];
        long long dx = (long long)queries[i][1];

        if (cmd == 0) { // (정방향) 왼쪽 -> (역방향) 오른쪽으로 되돌림
            if (c1 != 0) c1 += dx;
            c2 = (c2 + dx < (long long)m - 1) ? (c2 + dx) : (long long)m - 1;

        } else if (cmd == 1) { // (정방향) 오른쪽 -> (역방향) 왼쪽으로 되돌림
            c1 = (c1 - dx > 0) ? (c1 - dx) : 0;
            if (c2 != (long long)m - 1) c2 -= dx;

        } else if (cmd == 2) { // (정방향) 위 -> (역방향) 아래로 되돌림
            if (r1 != 0) r1 += dx;
            r2 = (r2 + dx < (long long)n - 1) ? (r2 + dx) : (long long)n - 1;

        } else { // cmd == 3 (정방향) 아래 -> (역방향) 위로 되돌림
            r1 = (r1 - dx > 0) ? (r1 - dx) : 0;
            if (r2 != (long long)n - 1) r2 -= dx;
        }

        // 범위가 뒤집히면 가능한 시작점 없음
        if (r1 > r2 || c1 > c2) return 0;
    }

    return (r2 - r1 + 1) * (c2 - c1 + 1);
}