#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

int solution(int** beginning, size_t beginning_rows, size_t beginning_cols,
             int** target, size_t target_rows, size_t target_cols) {

    (void)target_rows; (void)target_cols;

    int R = (int)beginning_rows;
    int C = (int)beginning_cols;

    // diff[i][j] = beginning XOR target
    int *diff = (int*)malloc((size_t)R * (size_t)C * sizeof(int));
    if (!diff) return -1;

    for (int i = 0; i < R; i++) {
        for (int j = 0; j < C; j++) {
            diff[i * C + j] = beginning[i][j] ^ target[i][j];
        }
    }

    int best = INT_MAX;
    int totalMasks = 1 << R;

    int *colFlip = (int*)malloc((size_t)C * sizeof(int));
    if (!colFlip) { free(diff); return -1; }

    for (int mask = 0; mask < totalMasks; mask++) {
        // 0행을 0으로 만들도록 열 뒤집기 결정
        int rf0 = (mask & 1); // rowFlip(0)
        int colCount = 0;

        for (int j = 0; j < C; j++) {
            colFlip[j] = diff[0 * C + j] ^ rf0;
            colCount += colFlip[j];
        }

        // 전체가 0이 되는지 검증
        bool ok = true;
        for (int i = 0; i < R && ok; i++) {
            int rfi = (mask >> i) & 1;
            for (int j = 0; j < C; j++) {
                int v = diff[i * C + j] ^ rfi ^ colFlip[j];
                if (v != 0) { ok = false; break; }
            }
        }

        if (ok) {
            // mask의 1비트 개수 = 행 뒤집기 횟수
            int rowCount = __builtin_popcount((unsigned)mask);
            int ops = rowCount + colCount;
            if (ops < best) best = ops;
        }
    }

    free(colFlip);
    free(diff);

    return (best == INT_MAX) ? -1 : best;
}