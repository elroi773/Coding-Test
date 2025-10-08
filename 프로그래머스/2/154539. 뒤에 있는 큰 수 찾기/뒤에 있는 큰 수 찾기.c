#include <stdio.h>
#include <stdlib.h>

int* solution(int numbers[], size_t numbers_len) {
    int* answer = (int*)malloc(sizeof(int) * numbers_len);
    int* stack = (int*)malloc(sizeof(int) * numbers_len); // 인덱스를 저장할 스택
    int top = -1; // 스택 포인터

    // 초기값은 -1로 설정 (뒷 큰수가 없는 경우)
    for (size_t i = 0; i < numbers_len; i++) {
        answer[i] = -1;
    }

    for (size_t i = 0; i < numbers_len; i++) {
        // 스택이 비어있지 않고, 현재 값이 스택 최상단 값보다 큰 경우
        while (top >= 0 && numbers[i] > numbers[stack[top]]) {
            answer[stack[top]] = numbers[i]; // 뒷 큰수 기록
            top--; // 스택에서 제거
        }
        stack[++top] = i; // 현재 인덱스를 스택에 추가
    }

    free(stack);
    return answer;
}