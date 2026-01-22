#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

static int N;
static int wolfMask;
static int children[17][2];
static int childCnt[17];
static bool seen[1 << 17];
static int best;

static int popcount(int x) {
    int c = 0;
    while (x) {
        x &= (x - 1);
        c++;
    }
    return c;
}

static int nextCandidates(int mask) {
    int cand = 0;
    for (int i = 0; i < N; i++) {
        if (mask & (1 << i)) {
            for (int k = 0; k < childCnt[i]; k++) {
                cand |= (1 << children[i][k]);
            }
        }
    }
    cand &= ~mask; // already visited 제외
    return cand;
}

static void dfs(int mask) {
    if (seen[mask]) return;
    seen[mask] = true;

    int wolves = popcount(mask & wolfMask);
    int sheep  = popcount(mask) - wolves;

    if (wolves >= sheep) return;

    if (sheep > best) best = sheep;

    int cand = nextCandidates(mask);
    while (cand) {
        int nxt = cand & -cand; // lowest set bit
        cand -= nxt;
        dfs(mask | nxt);
    }
}

// info_len은 배열 info의 길이입니다.
// edges_rows는 2차원 배열 edges의 행 길이, edges_cols는 2차원 배열 edges의 열 길이입니다.
int solution(int info[], size_t info_len, int** edges, size_t edges_rows, size_t edges_cols) {
    (void)edges_cols; // unused (always 2)

    N = (int)info_len;
    wolfMask = 0;
    best = 0;

    // init
    for (int i = 0; i < 17; i++) {
        childCnt[i] = 0;
        children[i][0] = children[i][1] = -1;
    }
    for (int i = 0; i < (1 << 17); i++) seen[i] = false;

    // wolf mask
    for (int i = 0; i < N; i++) {
        if (info[i] == 1) wolfMask |= (1 << i);
    }

    // build tree children (binary)
    for (size_t i = 0; i < edges_rows; i++) {
        int p = edges[i][0];
        int c = edges[i][1];
        children[p][childCnt[p]++] = c;
    }

    // start from root(0)
    dfs(1 << 0);
    return best;
}