#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// qsort에서 사용할 비교 함수: 끝점(e) 기준 오름차순 정렬
int cmp(const void *a, const void *b) {
    int *ia = *(int **)a;  // 각 요소는 int* (길이 2짜리 배열)
    int *ib = *(int **)b;

    if (ia[1] == ib[1]) {
        // 끝점이 같으면 시작점 기준으로 정렬 (선택 사항이지만 안정적)
        return ia[0] - ib[0];
    }
    return ia[1] - ib[1];
}

// targets_rows는 2차원 배열 targets의 행 길이, targets_cols는 2차원 배열 targets의 열 길이입니다.
int solution(int** targets, size_t targets_rows, size_t targets_cols) {
    (void)targets_cols; // 열 길이(=2)는 고정이라 사용하지 않아도 되므로 경고 방지용

    // 1. 끝점 기준 정렬
    qsort(targets, targets_rows, sizeof(int*), cmp);

    int answer = 0;
    int last = -1;  // 마지막 요격 미사일의 x좌표를 표현하는 값 (논리적으로는 e 바로 앞)

    // 2. 정렬된 구간을 앞에서부터 보면서 그리디하게 선택
    for (size_t i = 0; i < targets_rows; i++) {
        int s = targets[i][0];
        int e = targets[i][1];

        // 현재 구간의 시작이 last 이상이면,
        // 기존 미사일로 커버 못하므로 새로 한 발 쏨
        if (s >= last) {
            answer++;
            last = e;  // 이 구간의 끝점 바로 앞에 쏜 것으로 간주
        }
    }

    return answer;
}