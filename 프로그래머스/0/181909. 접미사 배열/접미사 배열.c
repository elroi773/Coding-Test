#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// 문자열 비교 함수 (qsort에 사용)
int compare(const void* a, const void* b) {
    const char* str1 = *(const char**)a;
    const char* str2 = *(const char**)b;
    return strcmp(str1, str2);
}

char** solution(const char* my_string) {
    int len = strlen(my_string);
    
    // 동적 할당: 접미사 수는 len개
    char** suffixes = (char**)malloc(sizeof(char*) * len);
    
    for (int i = 0; i < len; i++) {
        suffixes[i] = strdup(my_string + i);  // i번째 인덱스부터 끝까지 복사
    }

    // 사전순 정렬
    qsort(suffixes, len, sizeof(char*), compare);

    return suffixes;
}
