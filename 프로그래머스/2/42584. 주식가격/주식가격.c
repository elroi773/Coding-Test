#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// prices_len은 배열 prices의 길이입니다.
int* solution(int prices[], size_t prices_len) {
    // 결과 배열 동적 할당
    int* answer = (int*)malloc(sizeof(int) * prices_len);
    
    // 스택 (인덱스 저장)
    int* stack = (int*)malloc(sizeof(int) * prices_len);
    int top = -1;

    // 초기화
    for (int i = 0; i < prices_len; i++) {
        answer[i] = 0;
    }

    for (int i = 0; i < prices_len; i++) {
        // 가격이 떨어지는 순간
        while (top != -1 && prices[i] < prices[stack[top]]) {
            int idx = stack[top--];
            answer[idx] = i - idx;
        }
        stack[++top] = i;
    }

    // 끝까지 가격이 안 떨어진 경우
    while (top != -1) {
        int idx = stack[top--];
        answer[idx] = prices_len - 1 - idx;
    }

    free(stack);
    return answer;
}
