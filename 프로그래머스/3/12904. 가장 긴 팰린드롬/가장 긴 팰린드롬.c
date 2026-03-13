#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int expand(const char* s, int n, int left, int right) {
    while (left >= 0 && right < n && s[left] == s[right]) {
        left--;
        right++;
    }
    return right - left - 1;
}

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
int solution(const char* s) {
    int n = strlen(s);
    int answer = 1;

    for (int i = 0; i < n; i++) {
        int odd = expand(s, n, i, i);       // 홀수 길이
        int even = expand(s, n, i, i + 1);  // 짝수 길이

        if (odd > answer) answer = odd;
        if (even > answer) answer = even;
    }

    return answer;
}