#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

char* solution(const char* rny_string) {
    int len = strlen(rny_string);
    // 최악의 경우 모든 문자가 'm'인 경우 길이는 2배
    char* answer = (char*)malloc(len * 2 + 1); // +1은 널 문자('\0')를 위한 공간

    int idx = 0; // answer 배열의 인덱스
    for (int i = 0; i < len; i++) {
        if (rny_string[i] == 'm') {
            answer[idx++] = 'r';
            answer[idx++] = 'n';
        } else {
            answer[idx++] = rny_string[i];
        }
    }
    answer[idx] = '\0'; // 문자열 끝을 나타내는 null 문자 추가

    return answer;
}
