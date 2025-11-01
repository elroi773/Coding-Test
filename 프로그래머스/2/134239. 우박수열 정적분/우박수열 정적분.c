#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// ranges_rows는 2차원 배열 ranges의 행 길이, ranges_cols는 열 길이입니다.
double* solution(int k, int** ranges, size_t ranges_rows, size_t ranges_cols) {
    // 1️⃣ 우박수열 만들기 (최대 길이를 모르므로 동적 배열)
    int capacity = 10000; // 충분히 큰 초기 용량
    double* seq = (double*)malloc(sizeof(double) * capacity);
    int len = 0;
    double cur = (double)k;
    seq[len++] = cur;

    while (cur != 1.0) {
        if ((long)cur % 2 == 0) cur = cur / 2.0;
        else cur = cur * 3.0 + 1.0;

        if (len >= capacity) { // 필요 시 확장
            capacity *= 2;
            seq = (double*)realloc(seq, sizeof(double) * capacity);
        }
        seq[len++] = cur;
    }

    int n = len - 1; // 구간 개수는 len-1개

    // 2️⃣ 사다리꼴 넓이 누적합 (prefix sum)
    double* prefix = (double*)malloc(sizeof(double) * (n + 1));
    prefix[0] = 0.0;
    for (int i = 0; i < n; i++) {
        double area = (seq[i] + seq[i + 1]) / 2.0;
        prefix[i + 1] = prefix[i] + area;
    }

    // 3️⃣ 각 ranges에 대해 정적분 결과 계산
    double* answer = (double*)malloc(sizeof(double) * ranges_rows);
    for (size_t i = 0; i < ranges_rows; i++) {
        int a = ranges[i][0];
        int b = ranges[i][1];  // b ≤ 0
        int right = n + b;

        // 유효하지 않은 구간이면 -1.0
        if (a > right || a < 0 || right < 0 || a > n) {
            answer[i] = -1.0;
        } else {
            answer[i] = prefix[right] - prefix[a];
        }
    }

    free(seq);
    free(prefix);
    return answer; // 결과 배열 반환 (호출자에서 free 필요)
}
