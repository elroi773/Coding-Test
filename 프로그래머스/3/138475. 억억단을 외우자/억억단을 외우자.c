#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// starts_len은 배열 starts의 길이입니다.
int* solution(int e, int starts[], size_t starts_len) {
    // 1) 약수 개수 계산
    int *cnt = (int*)calloc((size_t)e + 1, sizeof(int));
    for (int i = 1; i <= e; i++) {
        for (int j = i; j <= e; j += i) {
            cnt[j]++; // i는 j의 약수
        }
    }

    // 2) best[i] = 구간 [i..e]에서 약수개수 최대(동점이면 더 작은 수)
    int *best = (int*)malloc(((size_t)e + 2) * sizeof(int));

    int bestNum = e;
    int bestCnt = cnt[e];
    best[e] = e;

    for (int i = e - 1; i >= 1; i--) {
        // 동점이면 더 작은 수(i)가 이겨야 하므로 >=
        if (cnt[i] >= bestCnt) {
            bestCnt = cnt[i];
            bestNum = i;
        }
        best[i] = bestNum;
    }

    // 3) 질의 답
    int *answer = (int*)malloc(starts_len * sizeof(int));
    for (size_t idx = 0; idx < starts_len; idx++) {
        answer[idx] = best[starts[idx]];
    }

    // 내부 배열 해제(정답 배열은 반환해야 하므로 free하면 안 됨)
    free(cnt);
    free(best);

    return answer;
}