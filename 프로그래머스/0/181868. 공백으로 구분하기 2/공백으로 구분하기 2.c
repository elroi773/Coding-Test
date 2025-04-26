#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

char** solution(const char* my_string) {
    // 먼저 my_string 복사 (왜냐하면 strtok는 원본 문자열을 바꿔버림)
    char* str = strdup(my_string);
    
    // 단어 수 세기
    int count = 0;
    char* temp = strtok(str, " ");
    while (temp != NULL) {
        count++;
        temp = strtok(NULL, " ");
    }
    
    free(str); // 복사한 거 메모리 해제 (다시 복사할 거라서)
    str = strdup(my_string);

    // 단어 개수만큼 char* 배열 생성
    char** answer = (char**)malloc(sizeof(char*) * count);

    int idx = 0;
    char* token = strtok(str, " ");
    while (token != NULL) {
        // 단어 복사해서 저장
        answer[idx] = (char*)malloc(strlen(token) + 1); // +1은 '\0' 포함
        strcpy(answer[idx], token);
        idx++;
        token = strtok(NULL, " ");
    }

    free(str); // 더 이상 필요 없으니 해제

    return answer;
}
