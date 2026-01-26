#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef long long ll;

typedef struct {
    int to;
    int next;
} Edge;

long long solution(int a[], size_t a_len, int** edges, size_t edges_rows, size_t edges_cols) {
    int n = (int)a_len;
    if (n == 0) return 0;

    // sum(a) != 0 이면 불가능
    ll sum = 0;
    for (int i = 0; i < n; i++) sum += (ll)a[i];
    if (sum != 0) return -1;

    // 인접 리스트 (헤드/next 방식)
    int m = (int)edges_rows;          // 트리이므로 m = n-1
    int E = 2 * m;
    int *head = (int*)malloc(sizeof(int) * n);
    Edge *adj = (Edge*)malloc(sizeof(Edge) * E);
    if (!head || !adj) {
        free(head); free(adj);
        return -1; // 메모리 실패는 보통 채점에 없지만 안전 처리
    }
    for (int i = 0; i < n; i++) head[i] = -1;

    int idx = 0;
    for (int i = 0; i < m; i++) {
        int u = edges[i][0];
        int v = edges[i][1];

        adj[idx].to = v;
        adj[idx].next = head[u];
        head[u] = idx++;

        adj[idx].to = u;
        adj[idx].next = head[v];
        head[v] = idx++;
    }

    // parent, order(DFS 방문순서), stack
    int *parent = (int*)malloc(sizeof(int) * n);
    int *order  = (int*)malloc(sizeof(int) * n);
    int *stack  = (int*)malloc(sizeof(int) * n);
    if (!parent || !order || !stack) {
        free(head); free(adj);
        free(parent); free(order); free(stack);
        return -1;
    }
    for (int i = 0; i < n; i++) parent[i] = -1;

    int top = 0, ord_sz = 0;
    stack[top++] = 0;
    parent[0] = 0;

    // 스택 DFS로 방문 순서(order) 만들기
    while (top > 0) {
        int u = stack[--top];
        order[ord_sz++] = u;

        for (int e = head[u]; e != -1; e = adj[e].next) {
            int v = adj[e].to;
            if (parent[v] == -1) {
                parent[v] = u;
                stack[top++] = v;
            }
        }
    }

    // 값은 long long으로 누적/전파 (a[i] 범위 * n 고려)
    ll *val = (ll*)malloc(sizeof(ll) * n);
    if (!val) {
        free(head); free(adj);
        free(parent); free(order); free(stack);
        return -1;
    }
    for (int i = 0; i < n; i++) val[i] = (ll)a[i];

    // 후위 처리: order를 역순으로(루트 제외)
    ll ans = 0;
    for (int i = ord_sz - 1; i >= 1; i--) {
        int u = order[i];
        ll x = val[u];
        ans += (x >= 0 ? x : -x);
        int p = parent[u];
        val[p] += x;
        val[u] = 0;
    }

    // 정리
    free(head);
    free(adj);
    free(parent);
    free(order);
    free(stack);
    free(val);

    return ans;
}