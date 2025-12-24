#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

int toDecimal(const char* s, int base) {
    int value = 0;
    for (int i = 0; s[i]; i++) {
        int d = s[i] - '0';
        if (d < 0 || d >= base) return -1;
        value = value * base + d;
    }
    return value;
}

char* toBase(int value, int base) {
    static char buf[20];
    int idx = 0;

    if (value == 0) {
        buf[0] = '0';
        buf[1] = '\0';
        return buf;
    }

    while (value > 0) {
        buf[idx++] = (value % base) + '0';
        value /= base;
    }
    buf[idx] = '\0';

    for (int i = 0; i < idx / 2; i++) {
        char t = buf[i];
        buf[i] = buf[idx - i - 1];
        buf[idx - i - 1] = t;
    }

    return buf;
}

char** solution(const char* expressions[], size_t expressions_len) {
    bool validBase[10] = { false };
    int validCount = 0;

    // 1️⃣ 진법 후보 찾기
    for (int base = 2; base <= 9; base++) {
        bool ok = true;

        for (size_t i = 0; i < expressions_len; i++) {
            char A[10], B[10], C[10], op;
            sscanf(expressions[i], "%s %c %s = %s", A, &op, B, C);

            int a = toDecimal(A, base);
            int b = toDecimal(B, base);
            if (a < 0 || b < 0) {
                ok = false;
                break;
            }

            if (strcmp(C, "X") != 0) {
                int c = toDecimal(C, base);
                if (c < 0) {
                    ok = false;
                    break;
                }

                int r = (op == '+') ? a + b : a - b;
                if (r != c) {
                    ok = false;
                    break;
                }
            }
        }

        if (ok) {
            validBase[base] = true;
            validCount++;
        }
    }

    // X 수식 개수 세기
    int xCount = 0;
    for (size_t i = 0; i < expressions_len; i++) {
        if (strstr(expressions[i], "= X")) xCount++;
    }

    char** answer = (char**)malloc(sizeof(char*) * xCount);
    int idx = 0;

    // 2️⃣ X 계산
    for (size_t i = 0; i < expressions_len; i++) {
        if (!strstr(expressions[i], "= X")) continue;

        char A[10], B[10], C[10], op;
        sscanf(expressions[i], "%s %c %s = %s", A, &op, B, C);

        char result[20] = "";
        bool first = true;
        bool same = true;

        for (int base = 2; base <= 9; base++) {
            if (!validBase[base]) continue;

            int a = toDecimal(A, base);
            int b = toDecimal(B, base);
            int r = (op == '+') ? a + b : a - b;

            char* cur = toBase(r, base);

            if (first) {
                strcpy(result, cur);
                first = false;
            } else if (strcmp(result, cur) != 0) {
                same = false;
                break;
            }
        }

        char buf[50];
        if (same)
            sprintf(buf, "%s %c %s = %s", A, op, B, result);
        else
            sprintf(buf, "%s %c %s = ?", A, op, B);

        answer[idx] = (char*)malloc(strlen(buf) + 1);
        strcpy(answer[idx], buf);
        idx++;
    }

    return answer;
}
