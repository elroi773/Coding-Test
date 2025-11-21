#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h> // memset

// elements_len은 배열 elements의 길이입니다.
int solution(int elements[], size_t elements_len) {
    size_t n = elements_len;

    // 1. 원형 처리를 위해 배열 2배로 확장
    int *extended = (int *)malloc(sizeof(int) * (2 * n));
    if (!extended) return 0; // 메모리 할당 실패 방지용

    for (size_t i = 0; i < 2 * n; i++) {
        extended[i] = elements[i % n];
    }

    // 2. prefix sum 배열 (길이: 2n + 1)
    int *prefix = (int *)malloc(sizeof(int) * (2 * n + 1));
    if (!prefix) {
        free(extended);
        return 0;
    }

    prefix[0] = 0;
    for (size_t i = 0; i < 2 * n; i++) {
        prefix[i + 1] = prefix[i] + extended[i];
    }

    // 3. 가능한 합을 기록할 배열
    // 최대 합: 1000(최대 값) * 1000(최대 길이) = 1,000,000
    const int MAX_SUM = 1000000;
    bool *seen = (bool *)malloc(sizeof(bool) * (MAX_SUM + 1));
    if (!seen) {
        free(extended);
        free(prefix);
        return 0;
    }
    memset(seen, 0, sizeof(bool) * (MAX_SUM + 1));

    int answer = 0;

    // 4. 부분 수열 길이 1 ~ n
    for (size_t length = 1; length <= n; length++) {
        // 시작 위치 0 ~ n-1 (원형에서 한 바퀴만 보면 됨)
        for (size_t start = 0; start < n; start++) {
            int sum = prefix[start + length] - prefix[start];
            if (!seen[sum]) {
                seen[sum] = true;
                answer++;
            }
        }
    }

    // 5. 메모리 해제
    free(extended);
    free(prefix);
    free(seen);

    return answer;
}
