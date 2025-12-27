#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

typedef struct {
    int a; // request time
    int b; // duration
} Req;

/* ---------- Min-Heap for int (end times) ---------- */
typedef struct {
    int *data;
    int size;
} MinHeap;

static void heap_init(MinHeap *h, int capacity) {
    h->data = (int*)malloc(sizeof(int) * capacity);
    h->size = 0;
}

static void heap_free(MinHeap *h) {
    free(h->data);
    h->data = NULL;
    h->size = 0;
}

static void heap_push(MinHeap *h, int x) {
    int i = h->size++;
    h->data[i] = x;
    while (i > 0) {
        int p = (i - 1) / 2;
        if (h->data[p] <= h->data[i]) break;
        int tmp = h->data[p];
        h->data[p] = h->data[i];
        h->data[i] = tmp;
        i = p;
    }
}

static int heap_pop_min(MinHeap *h) {
    int ret = h->data[0];
    h->size--;
    if (h->size > 0) {
        h->data[0] = h->data[h->size];
        int i = 0;
        while (1) {
            int l = i * 2 + 1;
            int r = i * 2 + 2;
            int smallest = i;

            if (l < h->size && h->data[l] < h->data[smallest]) smallest = l;
            if (r < h->size && h->data[r] < h->data[smallest]) smallest = r;
            if (smallest == i) break;

            int tmp = h->data[i];
            h->data[i] = h->data[smallest];
            h->data[smallest] = tmp;
            i = smallest;
        }
    }
    return ret;
}

/* ---------- simulate total waiting time for one type with m mentors ---------- */
static long long calc_wait(const Req *list, int len, int m) {
    if (len == 0) return 0;

    MinHeap heap;
    heap_init(&heap, m); // heap size never exceeds m
    long long total_wait = 0;

    for (int i = 0; i < len; i++) {
        int a = list[i].a;
        int b = list[i].b;

        if (heap.size < m) {
            heap_push(&heap, a + b);
        } else {
            int earliest_end = heap_pop_min(&heap);
            int start_time = (a > earliest_end) ? a : earliest_end;
            total_wait += (long long)(start_time - a);
            heap_push(&heap, start_time + b);
        }
    }

    heap_free(&heap);
    return total_wait;
}

// reqs_rows는 2차원 배열 reqs의 행 길이, reqs_cols는 2차원 배열 reqs의 열 길이입니다.
int solution(int k, int n, int** reqs, size_t reqs_rows, size_t reqs_cols) {
    (void)reqs_cols; // 항상 3이지만 unused 경고 방지

    // 1) count requests by type
    int *cnt = (int*)calloc((size_t)k + 1, sizeof(int));
    for (size_t i = 0; i < reqs_rows; i++) {
        int c = reqs[i][2];
        cnt[c]++;
    }

    // 2) allocate and fill per-type request lists (a is globally sorted, so per-type order is also sorted)
    Req **by_type = (Req**)calloc((size_t)k + 1, sizeof(Req*));
    int *idx = (int*)calloc((size_t)k + 1, sizeof(int));
    for (int t = 1; t <= k; t++) {
        if (cnt[t] > 0) by_type[t] = (Req*)malloc(sizeof(Req) * (size_t)cnt[t]);
    }

    for (size_t i = 0; i < reqs_rows; i++) {
        int a = reqs[i][0];
        int b = reqs[i][1];
        int c = reqs[i][2];
        by_type[c][idx[c]].a = a;
        by_type[c][idx[c]].b = b;
        idx[c]++;
    }

    // 3) precompute cost[t][m]
    long long cost[6][21]; // k<=5, n<=20
    for (int t = 1; t <= k; t++) {
        for (int m = 1; m <= n; m++) {
            cost[t][m] = calc_wait(by_type[t], cnt[t], m);
        }
    }

    // 4) DP distribute n mentors across k types (each >= 1)
    const long long INF = (long long)LLONG_MAX / 4;
    long long dp[6][21];
    for (int i = 0; i <= k; i++) {
        for (int j = 0; j <= n; j++) dp[i][j] = INF;
    }
    dp[0][0] = 0;

    for (int i = 1; i <= k; i++) {
        for (int j = i; j <= n; j++) { // at least 1 per type => j>=i
            int max_m = j - (i - 1);    // leave at least (i-1) for previous types
            for (int m = 1; m <= max_m; m++) {
                if (dp[i - 1][j - m] == INF) continue;
                long long cand = dp[i - 1][j - m] + cost[i][m];
                if (cand < dp[i][j]) dp[i][j] = cand;
            }
        }
    }

    // cleanup
    for (int t = 1; t <= k; t++) free(by_type[t]);
    free(by_type);
    free(cnt);
    free(idx);

    return (int)dp[k][n];
}