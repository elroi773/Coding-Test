#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct {
    int a;
    int b;
} Score;

// 정렬 기준: a 내림차순, b 오름차순
int compare(const void* x, const void* y) {
    Score* s1 = (Score*)x;
    Score* s2 = (Score*)y;

    if (s1->a != s2->a)
        return s2->a - s1->a;   // 근무태도 내림차순
    return s1->b - s2->b;       // 동료평가 오름차순
}

// scores_rows는 2차원 배열 scores의 행 길이
int solution(int** scores, size_t scores_rows, size_t scores_cols) {
    int wanhoA = scores[0][0];
    int wanhoB = scores[0][1];
    int wanhoSum = wanhoA + wanhoB;

    Score* arr = (Score*)malloc(sizeof(Score) * scores_rows);
    for (size_t i = 0; i < scores_rows; i++) {
        arr[i].a = scores[i][0];
        arr[i].b = scores[i][1];
    }

    qsort(arr, scores_rows, sizeof(Score), compare);

    int maxB = 0;
    int rank = 1;

    for (size_t i = 0; i < scores_rows; i++) {
        int a = arr[i].a;
        int b = arr[i].b;

        // 지배당한 경우
        if (b < maxB) {
            if (a == wanhoA && b == wanhoB) {
                free(arr);
                return -1;
            }
            continue;
        }

        maxB = b;

        if (a + b > wanhoSum)
            rank++;
    }

    free(arr);
    return rank;
}
