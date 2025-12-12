#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int compare(const void *a, const void *b) {
    char ab[10], ba[10];
    sprintf(ab, "%s%s", *(char**)a, *(char**)b);
    sprintf(ba, "%s%s", *(char**)b, *(char**)a);
    return strcmp(ba, ab);   // 내림차순 정렬
}

char* solution(int numbers[], size_t numbers_len) {
    // 1) 숫자를 문자열로 변환
    char **strs = malloc(sizeof(char*) * numbers_len);
    for (int i = 0; i < numbers_len; i++) {
        strs[i] = malloc(5);             // 최대 1000 → 4자리 + '\0'
        sprintf(strs[i], "%d", numbers[i]);
    }

    // 2) 정렬 (커스텀 비교)
    qsort(strs, numbers_len, sizeof(char*), compare);

    // 3) 모든 숫자가 0인 경우 처리
    if (strcmp(strs[0], "0") == 0) {
        char *answer = malloc(2);
        strcpy(answer, "0");
        return answer;
    }

    // 4) 결과 문자열 길이 계산
    int total_len = 1;
    for (int i = 0; i < numbers_len; i++) total_len += strlen(strs[i]);

    // 5) 결과 문자열 생성
    char *answer = malloc(total_len);
    answer[0] = '\0';

    for (int i = 0; i < numbers_len; i++) {
        strcat(answer, strs[i]);
        free(strs[i]);
    }
    free(strs);

    return answer;
}
