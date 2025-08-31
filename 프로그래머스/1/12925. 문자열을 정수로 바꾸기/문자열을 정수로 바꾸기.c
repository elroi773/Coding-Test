#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(const char* s) {
    int answer = 0;
    int sign = 1; // 부호 (+1 또는 -1)

    // 첫 글자가 '-'면 음수 처리
    if (s[0] == '-') {
        sign = -1;
        s++; // 부호 건너뛰기
    } 
    // 첫 글자가 '+'면 건너뛰기
    else if (s[0] == '+') {
        s++;
    }

    // 각 문자를 정수로 변환
    while (*s) {
        answer = answer * 10 + (*s - '0');
        s++;
    }

    return sign * answer;
}
