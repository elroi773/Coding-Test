#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int answer;
bool col[12];
bool diag1[25]; // row - col + n
bool diag2[25]; // row + col

void backtrack(int row, int n) {
    if (row == n) {
        answer++;
        return;
    }

    for (int c = 0; c < n; c++) {
        if (col[c] || diag1[row - c + n] || diag2[row + c])
            continue;

        // 퀸 배치
        col[c] = diag1[row - c + n] = diag2[row + c] = true;

        backtrack(row + 1, n);

        // 백트래킹
        col[c] = diag1[row - c + n] = diag2[row + c] = false;
    }
}

int solution(int n) {
    answer = 0;

    // 배열 초기화
    for (int i = 0; i < 12; i++) col[i] = false;
    for (int i = 0; i < 25; i++) diag1[i] = diag2[i] = false;

    backtrack(0, n);
    return answer;
}
