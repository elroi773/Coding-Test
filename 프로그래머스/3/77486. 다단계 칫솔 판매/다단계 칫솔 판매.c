#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>
#include <stdint.h>

static unsigned long hash_str(const char *s) {
    // djb2
    unsigned long h = 5381UL;
    unsigned char c;
    while ((c = (unsigned char)*s++)) {
        h = ((h << 5) + h) + c; // h*33 + c
    }
    return h;
}

static size_t next_pow2(size_t x) {
    size_t p = 1;
    while (p < x) p <<= 1;
    return p;
}

typedef struct {
    const char **keys; // points to enroll strings
    int *vals;         // index
    size_t cap;
} HashMap;

static void hm_init(HashMap *hm, size_t n) {
    hm->cap = next_pow2(n * 2 + 1); // load factor <= ~0.5
    hm->keys = (const char **)calloc(hm->cap, sizeof(const char *));
    hm->vals = (int *)malloc(hm->cap * sizeof(int));
}

static void hm_put(HashMap *hm, const char *key, int val) {
    size_t cap = hm->cap;
    size_t i = (size_t)(hash_str(key) & (cap - 1));

    while (hm->keys[i] != NULL) {
        if (strcmp(hm->keys[i], key) == 0) {
            hm->vals[i] = val;
            return;
        }
        i = (i + 1) & (cap - 1);
    }
    hm->keys[i] = key;
    hm->vals[i] = val;
}

static int hm_get(HashMap *hm, const char *key) {
    size_t cap = hm->cap;
    size_t i = (size_t)(hash_str(key) & (cap - 1));

    while (hm->keys[i] != NULL) {
        if (strcmp(hm->keys[i], key) == 0) return hm->vals[i];
        i = (i + 1) & (cap - 1);
    }
    return -1; // not found
}

// enroll_len은 배열 enroll의 길이입니다.
// referral_len은 배열 referral의 길이입니다.
// seller_len은 배열 seller의 길이입니다.
// amount_len은 배열 amount의 길이입니다.
int* solution(const char* enroll[], size_t enroll_len,
              const char* referral[], size_t referral_len,
              const char* seller[], size_t seller_len,
              int amount[], size_t amount_len) {
    (void)referral_len; // 문제 조건상 enroll_len과 동일
    (void)amount_len;   // 문제 조건상 seller_len과 동일

    // 이름 -> 인덱스 해시맵
    HashMap hm;
    hm_init(&hm, enroll_len);
    for (size_t i = 0; i < enroll_len; i++) {
        hm_put(&hm, enroll[i], (int)i);
    }

    // parent[i] = 추천인 인덱스, 없으면 -1(센터)
    int *parent = (int *)malloc(enroll_len * sizeof(int));
    for (size_t i = 0; i < enroll_len; i++) {
        if (referral[i][0] == '-' && referral[i][1] == '\0') {
            parent[i] = -1;
        } else {
            parent[i] = hm_get(&hm, referral[i]);
        }
    }

    // 이익(누적)
    long long *profit = (long long *)calloc(enroll_len, sizeof(long long));

    // 판매 기록 처리
    for (size_t i = 0; i < seller_len; i++) {
        int cur = hm_get(&hm, seller[i]);
        long long money = (long long)amount[i] * 100LL;

        while (cur != -1 && money > 0) {
            long long give = money / 10LL;      // 10% (원 단위 절사)
            long long keep = money - give;
            profit[cur] += keep;

            cur = parent[cur];
            money = give;                       // 위로 전달
        }
    }

    // 결과 반환
    int *answer = (int *)malloc(enroll_len * sizeof(int));
    for (size_t i = 0; i < enroll_len; i++) {
        answer[i] = (int)profit[i];
    }

    // 메모리 정리(채점에는 영향 없지만 깔끔하게)
    free(parent);
    free(profit);
    free(hm.keys);
    free(hm.vals);

    return answer;
}