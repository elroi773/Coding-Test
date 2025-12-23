#include <stdio.h>
#include <stdlib.h>

// 오름차순 비교 함수
int asc(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

// 내림차순 비교 함수
int desc(const void* a, const void* b) {
    return (*(int*)b - *(int*)a);
}

int solution(int A[], size_t A_len, int B[], size_t B_len) {
    int answer = 0;

    // A 오름차순, B 내림차순 정렬
    qsort(A, A_len, sizeof(int), asc);
    qsort(B, B_len, sizeof(int), desc);

    // 곱해서 누적
    for (size_t i = 0; i < A_len; i++) {
        answer += A[i] * B[i];
    }

    return answer;
}
