#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// 트리 유효성 검사
bool isValid(char* tree, int len) {
    if (len == 1) return true;

    int mid = len / 2;

    // 루트가 0인데 하위에 1 존재 → 불가능
    if (tree[mid] == '0') {
        for (int i = 0; i < len; i++) {
            if (tree[i] == '1') return false;
        }
    }

    return isValid(tree, mid) && isValid(tree + mid + 1, mid);
}

// numbers_len은 배열 numbers의 길이입니다.
int* solution(long long numbers[], size_t numbers_len) {
    int* answer = (int*)malloc(sizeof(int) * numbers_len);

    for (size_t i = 0; i < numbers_len; i++) {
        long long num = numbers[i];

        // 1️⃣ 이진수 문자열 만들기
        char bin[65] = {0};
        int idx = 0;
        while (num > 0) {
            bin[idx++] = (num % 2) + '0';
            num /= 2;
        }
        if (idx == 0) bin[idx++] = '0';

        // 문자열 뒤집기
        for (int l = 0, r = idx - 1; l < r; l++, r--) {
            char tmp = bin[l];
            bin[l] = bin[r];
            bin[r] = tmp;
        }

        // 2️⃣ 포화 이진트리 길이 계산
        int len = 1;
        while (len < idx) {
            len = len * 2 + 1;
        }

        // 3️⃣ 앞에 0 패딩
        char* tree = (char*)malloc(len + 1);
        memset(tree, '0', len - idx);
        memcpy(tree + (len - idx), bin, idx);
        tree[len] = '\0';

        // 4️⃣ 검사
        answer[i] = isValid(tree, len) ? 1 : 0;

        free(tree);
    }

    return answer;
}
