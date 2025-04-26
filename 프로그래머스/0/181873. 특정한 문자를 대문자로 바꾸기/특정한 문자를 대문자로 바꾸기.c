#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h> // strlen 사용하려고

char* solution(const char* my_string, const char* alp) {
    int len = strlen(my_string);
    char* answer = (char*)malloc(sizeof(char) * (len + 1)); // +1은 '\0' 위해!

    for (int i = 0; i < len; i++) {
        if (my_string[i] == alp[0]) {
            // alp랑 같은 글자는 대문자로
            answer[i] = my_string[i] - ('a' - 'A');
        } else {
            answer[i] = my_string[i];
        }
    }
    answer[len] = '\0'; // 문자열 끝 처리 꼭!

    return answer;
}
