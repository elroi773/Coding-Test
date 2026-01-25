#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// s_len은 배열 s의 길이입니다.
// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
char** solution(const char* s[], size_t s_len) {
    char** answer = (char**)malloc(sizeof(char*) * s_len);

    for (size_t idx = 0; idx < s_len; idx++) {
        const char* str = s[idx];
        int n = (int)strlen(str);

        // 스택(남는 문자들)
        char* stack = (char*)malloc((size_t)n + 1);
        int top = 0;

        int cnt110 = 0;

        // 1) "110" 제거(스택 사용)
        for (int i = 0; i < n; i++) {
            stack[top++] = str[i];

            if (top >= 3 &&
                stack[top - 3] == '1' &&
                stack[top - 2] == '1' &&
                stack[top - 1] == '0') {
                top -= 3;
                cnt110++;
            }
        }

        // 2) 삽입 위치: 남은 문자열(stack[0..top-1])에서 마지막 '0' 뒤
        int insertIdx = 0; // 기본: 맨 앞
        for (int i = top - 1; i >= 0; i--) {
            if (stack[i] == '0') {
                insertIdx = i + 1;
                break;
            }
        }

        // 3) 결과 조립: prefix + ("110"*cnt110) + suffix
        int resLen = top + cnt110 * 3;
        char* res = (char*)malloc((size_t)resLen + 1);

        int p = 0;

        // prefix
        if (insertIdx > 0) {
            memcpy(res + p, stack, (size_t)insertIdx);
            p += insertIdx;
        }

        // "110" 반복 삽입
        for (int k = 0; k < cnt110; k++) {
            res[p++] = '1';
            res[p++] = '1';
            res[p++] = '0';
        }

        // suffix
        if (insertIdx < top) {
            memcpy(res + p, stack + insertIdx, (size_t)(top - insertIdx));
            p += (top - insertIdx);
        }

        res[p] = '\0';

        free(stack);
        answer[idx] = res;
    }

    return answer;
}