#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
int* solution(const char* s) {
    int* answer = (int*)malloc(sizeof(int) * 2); // [변환 횟수, 제거된 0 개수]

    // s를 변경해야 하므로 복사해서 사용
    size_t len = strlen(s);
    char* cur = (char*)malloc(len + 1);
    strcpy(cur, s);

    int transform_cnt = 0;
    int removed_zero_cnt = 0;

    while (!(len == 1 && cur[0] == '1')) {
        int ones = 0;
        for (size_t i = 0; i < len; i++) {
            if (cur[i] == '1') ones++;
        }
        int zeros = (int)len - ones;
        removed_zero_cnt += zeros;
        transform_cnt++;

        // 다음 문자열은 ones의 2진수 표현
        // ones >= 1 이므로 최소 1비트
        int tmp = ones;
        int bin_len = 0;
        while (tmp > 0) {
            bin_len++;
            tmp >>= 1;
        }

        char* next = (char*)malloc(bin_len + 1);
        next[bin_len] = '\0';
        for (int i = bin_len - 1; i >= 0; i--) {
            next[i] = (ones & 1) ? '1' : '0';
            ones >>= 1;
        }

        free(cur);
        cur = next;
        len = bin_len;
    }

    free(cur);

    answer[0] = transform_cnt;
    answer[1] = removed_zero_cnt;
    return answer;
}