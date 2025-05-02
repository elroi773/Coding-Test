#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <ctype.h>  // tolower 함수 사용

char* solution(const char* myString) {
    // 입력 문자열 길이 측정
    size_t len = strlen(myString);

    // 결과 문자열 메모리 할당 (+1은 널 문자 '\0' 위한 공간)
    char* answer = (char*)malloc(len + 1);

    // 소문자로 변환
    for (size_t i = 0; i < len; i++) {
        answer[i] = tolower(myString[i]);
    }

    // 문자열 끝 처리
    answer[len] = '\0';

    return answer;
}
