#include <stdio.h>
#include <stdbool.h>
#include <string.h>

bool is_valid(const char* str, int len) {
    char stack[1000];
    int top = -1;

    for (int i = 0; i < len; i++) {
        char c = str[i];

        if (c == '(' || c == '[' || c == '{') {
            stack[++top] = c;
        } else {
            if (top == -1) return false;

            char t = stack[top];
            if ((c == ')' && t != '(') ||
                (c == ']' && t != '[') ||
                (c == '}' && t != '{'))
                return false;

            top--;
        }
    }
    return top == -1;
}

int solution(const char* s) {
    int len = strlen(s);
    int answer = 0;

    char rotated[2000];  // 회전 문자열 저장용

    for (int x = 0; x < len; x++) {
        // 회전: s[x:], s[:x]
        int idx = 0;
        for (int i = x; i < len; i++)
            rotated[idx++] = s[i];
        for (int i = 0; i < x; i++)
            rotated[idx++] = s[i];
        rotated[idx] = '\0';

        if (is_valid(rotated, len))
            answer++;
    }

    return answer;
}
