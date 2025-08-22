#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int ingredient[], size_t ingredient_len) {
    int answer = 0;
    
    // 스택 역할을 할 배열
    int *stack = (int *)malloc(sizeof(int) * ingredient_len);
    int top = 0;  // stack의 현재 위치 (size)
    
    for (size_t i = 0; i < ingredient_len; i++) {
        stack[top++] = ingredient[i]; // push
        
        // 스택에 최소 4개 이상 있어야 햄버거 검사 가능
        if (top >= 4) {
            // 마지막 4개가 [1,2,3,1] 인지 확인
            if (stack[top-4] == 1 && stack[top-3] == 2 &&
                stack[top-2] == 3 && stack[top-1] == 1) {
                top -= 4; // pop 4개
                answer++;
            }
        }
    }
    
    free(stack);
    return answer;
}
