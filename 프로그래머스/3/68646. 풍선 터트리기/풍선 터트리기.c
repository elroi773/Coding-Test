#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

// a_len은 배열 a의 길이입니다.
int solution(int a[], size_t a_len) {
    if (a_len == 0) return 0;
    if (a_len == 1) return 1;

    // rightMin[i] = min(a[i+1..end]) (없으면 INF)
    long long INF = (long long)LLONG_MAX;
    long long *rightMin = (long long *)malloc(sizeof(long long) * a_len);
    if (!rightMin) return 0; // 메모리 부족 시 방어

    long long cur = INF;
    for (size_t idx = a_len; idx-- > 0;) {
        rightMin[idx] = cur;
        if ((long long)a[idx] < cur) cur = (long long)a[idx];
    }

    int answer = 0;
    long long leftMin = INF;
    for (size_t i = 0; i < a_len; i++) {
        long long ai = (long long)a[i];
        if (ai <= leftMin || ai <= rightMin[i]) {
            answer++;
        }
        if (ai < leftMin) leftMin = ai;
    }

    free(rightMin);
    return answer;
}