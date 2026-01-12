#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

// board[idx] = (board[idx] + t) % 4  (t는 0~3)
static inline void addMod4(int *board, int idx, int t) {
    board[idx] = (board[idx] + t) & 3; // 0~3이므로 mod 4는 &3로 가능
}

// (r,c)를 t번(0~3) 누르는 효과: 자기 + 상하좌우가 t만큼 회전
static inline void press(int *board, int n, int r, int c, int t) {
    if (t == 0) return;
    int idx = r * n + c;
    addMod4(board, idx, t);                 // self
    if (r > 0)     addMod4(board, (r-1)*n + c, t); // up
    if (r < n - 1) addMod4(board, (r+1)*n + c, t); // down
    if (c > 0)     addMod4(board, r*n + (c-1), t); // left
    if (c < n - 1) addMod4(board, r*n + (c+1), t); // right
}

int solution(int** clockHands, size_t clockHands_rows, size_t clockHands_cols) {
    (void)clockHands_cols;
    int n = (int)clockHands_rows;
    int size = n * n;

    // 원본을 1D로 저장
    int *orig = (int*)malloc((size_t)size * sizeof(int));
    if (!orig) return -1;

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            orig[i*n + j] = clockHands[i][j] & 3;
        }
    }

    int best = INT_MAX;

    // 4^n = 2^(2n) : 첫 행 각 칸(0~3회)을 2비트로 표현
    int cases = 1 << (2 * n);

    int *board = (int*)malloc((size_t)size * sizeof(int));
    if (!board) { free(orig); return -1; }

    for (int mask = 0; mask < cases; mask++) {
        // board = orig 복사
        for (int k = 0; k < size; k++) board[k] = orig[k];

        int cnt = 0;
        int tmp = mask;

        // 1) 첫 행 조작 적용
        for (int c = 0; c < n; c++) {
            int t = tmp & 3;   // 0~3
            tmp >>= 2;
            cnt += t;
            press(board, n, 0, c, t);
        }

        // 2) 2행~n행: 윗칸을 0으로 만들도록 강제 조작
        for (int r = 1; r < n; r++) {
            for (int c = 0; c < n; c++) {
                int above = board[(r-1)*n + c];
                if (above != 0) {
                    int t = (4 - above) & 3; // 1~3
                    cnt += t;
                    press(board, n, r, c, t);
                }
            }
        }

        // 3) 마지막 행이 모두 0이면 성공
        bool ok = true;
        for (int c = 0; c < n; c++) {
            if (board[(n-1)*n + c] != 0) { ok = false; break; }
        }

        if (ok && cnt < best) best = cnt;
    }

    free(board);
    free(orig);

    return (best == INT_MAX) ? -1 : best; // 문제 조건상 항상 가능이지만 안전하게
}