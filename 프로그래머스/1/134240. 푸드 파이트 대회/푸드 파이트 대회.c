#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// food_len은 배열 food의 길이입니다.
char* solution(int food[], size_t food_len) {
    // 총 길이 계산:
    // 각 food[i] / 2 * 2 (왼쪽 + 오른쪽) + 1 (중앙 0) + 1 (널 종료 문자)
    int length = 1; // 중앙 '0' 포함
    for (size_t i = 1; i < food_len; i++) {
        length += (food[i] / 2) * 2;
    }
    length += 1; // 문자열 끝 널 문자

    char* answer = (char*)malloc(sizeof(char) * length);
    if (!answer) return NULL;

    int pos = 0;

    // 왼쪽 부분 채우기
    for (size_t i = 1; i < food_len; i++) {
        int count = food[i] / 2;
        for (int j = 0; j < count; j++) {
            answer[pos++] = '0' + i;  // int -> char 변환 ('1', '2' 등)
        }
    }

    // 중앙 0 추가
    answer[pos++] = '0';

    // 오른쪽 부분 채우기 (왼쪽의 역순)
    for (int i = (pos - 2); i >= 0; i--) {
        answer[pos++] = answer[i];
    }

    answer[pos] = '\0'; // 문자열 종료

    return answer;
}
