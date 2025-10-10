#include <stdio.h>
#include <stdlib.h>

// 정렬용 전역 변수
int g_col;

// 비교 함수
int compare(const void* a, const void* b) {
    int* rowA = *(int**)a;
    int* rowB = *(int**)b;

    // col 기준 오름차순
    if (rowA[g_col - 1] != rowB[g_col - 1])
        return rowA[g_col - 1] - rowB[g_col - 1];
    // 첫 번째 컬럼 기준 내림차순
    return rowB[0] - rowA[0];
}

int solution(int** data, size_t data_rows, size_t data_cols, int col, int row_begin, int row_end) {
    g_col = col;

    // 1. 정렬
    qsort(data, data_rows, sizeof(int*), compare);

    // 2. XOR 누적 계산
    int answer = 0;
    for (int i = row_begin; i <= row_end; i++) {
        int sum = 0;
        for (int j = 0; j < data_cols; j++) {
            sum += data[i - 1][j] % i; // i는 1-based index
        }
        answer ^= sum; // XOR 누적
    }

    return answer;
}
