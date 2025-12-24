#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef unsigned long long ull;

ull pow26[12];

/* 문자열 → 순서 번호 (1-based) */
ull get_rank(const char* s) {
    int L = strlen(s);
    ull r = 0;

    // 더 짧은 문자열 개수
    for (int l = 1; l < L; l++) {
        r += pow26[l];
    }

    // 같은 길이에서 사전순
    for (int i = 0; i < L; i++) {
        r += (s[i] - 'a') * pow26[L - i - 1];
    }

    return r + 1;
}

/* qsort 비교 함수 */
int cmp(const void* a, const void* b) {
    ull x = *(ull*)a;
    ull y = *(ull*)b;
    if (x < y) return -1;
    if (x > y) return 1;
    return 0;
}

/* 순서 번호 → 문자열 */
char* get_string(ull k) {
    int L = 1;
    while (k > pow26[L]) {
        k -= pow26[L];
        L++;
    }

    k--;  // 0-based
    char* res = (char*)malloc(L + 1);
    res[L] = '\0';

    for (int i = 0; i < L; i++) {
        ull div = pow26[L - i - 1];
        res[i] = 'a' + (k / div);
        k %= div;
    }

    return res;
}

char* solution(long long n, const char* bans[], size_t bans_len) {
    // 26의 거듭제곱 미리 계산
    pow26[0] = 1;
    for (int i = 1; i <= 11; i++) {
        pow26[i] = pow26[i - 1] * 26;
    }

    // bans를 rank로 변환
    ull* banned = (ull*)malloc(sizeof(ull) * bans_len);
    for (size_t i = 0; i < bans_len; i++) {
        banned[i] = get_rank(bans[i]);
    }

    // 정렬
    qsort(banned, bans_len, sizeof(ull), cmp);

    // n 보정
    ull target = (ull)n;
    for (size_t i = 0; i < bans_len; i++) {
        if (banned[i] <= target) {
            target++;
        } else {
            break;
        }
    }

    free(banned);

    // 최종 문자열 반환
    return get_string(target);
}
