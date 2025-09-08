#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
bool solution(const char* s) {
    int len = strlen(s);

    // 길이가 4 또는 6인지 확인
    if (len != 4 && len != 6) {
        return false;
    }

    // 모든 문자가 숫자인지 확인
    for (int i = 0; i < len; i++) {
        if (!isdigit(s[i])) {
            return false;
        }
    }

    return true;
}