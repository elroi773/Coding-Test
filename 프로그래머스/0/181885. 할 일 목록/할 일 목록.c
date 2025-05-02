#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

char** solution(const char* todo_list[], size_t todo_list_len, bool finished[], size_t finished_len) {
    // 아직 마치지 못한 일의 개수 세기
    int count = 0;
    for (size_t i = 0; i < finished_len; i++) {
        if (!finished[i]) count++;
    }

    // 동적 메모리 할당: count개의 문자열 포인터
    char** answer = (char**)malloc(sizeof(char*) * count);

    // 아직 끝내지 못한 일들을 복사
    int idx = 0;
    for (size_t i = 0; i < todo_list_len; i++) {
        if (!finished[i]) {
            answer[idx] = strdup(todo_list[i]);  // 문자열 복사
            idx++;
        }
    }

    return answer;
}
