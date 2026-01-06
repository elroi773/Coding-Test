#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

static inline long long minll(long long a, long long b) {
    return (a < b) ? a : b;
}

int solution(int n, int** lighthouse, size_t lighthouse_rows, size_t lighthouse_cols) {
    (void)lighthouse_rows;
    (void)lighthouse_cols;

    int m = n - 1;
    int E = 2 * m;

    // adjacency list: head[u] -> (to[idx], next[idx]) ...
    int *head = (int*)malloc((n + 1) * sizeof(int));
    int *to   = (int*)malloc(E * sizeof(int));
    int *nxt  = (int*)malloc(E * sizeof(int));
    if (!head || !to || !nxt) {
        free(head); free(to); free(nxt);
        return 0;
    }

    for (int i = 1; i <= n; i++) head[i] = -1;

    int idx = 0;
    // add edge u->v
    #define ADD_EDGE(u, v) do {          \
        to[idx] = (v);                  \
        nxt[idx] = head[(u)];           \
        head[(u)] = idx;                \
        idx++;                          \
    } while(0)

    for (int i = 0; i < m; i++) {
        int a = lighthouse[i][0];
        int b = lighthouse[i][1];
        ADD_EDGE(a, b);
        ADD_EDGE(b, a);
    }

    int *parent = (int*)malloc((n + 1) * sizeof(int));
    int *stack  = (int*)malloc(n * sizeof(int));
    int *order  = (int*)malloc(n * sizeof(int));
    long long *dp0 = (long long*)malloc((n + 1) * sizeof(long long));
    long long *dp1 = (long long*)malloc((n + 1) * sizeof(long long));

    if (!parent || !stack || !order || !dp0 || !dp1) {
        free(head); free(to); free(nxt);
        free(parent); free(stack); free(order);
        free(dp0); free(dp1);
        return 0;
    }

    // iterative DFS to set parent and get traversal order
    for (int i = 1; i <= n; i++) parent[i] = 0;

    int top = 0, ordSz = 0;
    stack[top++] = 1;
    parent[1] = -1;

    while (top > 0) {
        int u = stack[--top];
        order[ordSz++] = u;

        for (int e = head[u]; e != -1; e = nxt[e]) {
            int v = to[e];
            if (v == parent[u]) continue;
            parent[v] = u;
            stack[top++] = v;
        }
    }

    // postorder DP: process reverse of order
    for (int i = ordSz - 1; i >= 0; i--) {
        int u = order[i];
        long long off = 0;      // dp0[u]
        long long on  = 1;      // dp1[u] includes selecting u

        for (int e = head[u]; e != -1; e = nxt[e]) {
            int v = to[e];
            if (v == parent[u]) continue;

            off += dp1[v];                 // u off -> child must be on
            on  += minll(dp0[v], dp1[v]);  // u on -> child choose min
        }

        dp0[u] = off;
        dp1[u] = on;
    }

    long long ans = minll(dp0[1], dp1[1]);

    free(head); free(to); free(nxt);
    free(parent); free(stack); free(order);
    free(dp0); free(dp1);

    return (int)ans;
}