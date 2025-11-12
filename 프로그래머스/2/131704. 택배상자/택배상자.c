#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int order[], size_t order_len) {
    int *stack = (int *)malloc(sizeof(int) * order_len);
    int top = -1;        // 스택의 top 인덱스
    int current = 1;     // 컨테이너에서 꺼낼 다음 상자 번호
    int answer = 0;      // 트럭에 실은 상자 수

    for (int i = 0; i < order_len; i++) {
        int target = order[i];

        // 필요한 상자가 나올 때까지 컨테이너에서 꺼내 스택에 저장
        while (current <= order_len && (top == -1 || stack[top] != target)) {
            stack[++top] = current;
            current++;
        }

        // 스택의 top이 지금 필요한 상자와 같다면 실음
        if (top != -1 && stack[top] == target) {
            top--; // pop
            answer++;
        } else {
            break; // 더 이상 불가능
        }
    }

    free(stack);
    return answer;
}
