#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// roads_rows는 2차원 배열 roads의 행 길이, roads_cols는 2차원 배열 roads의 열 길이입니다.
// sources_len은 배열 sources의 길이입니다.
int* solution(int n, int** roads, size_t roads_rows, size_t roads_cols,
              int sources[], size_t sources_len, int destination) {
    (void)roads_cols; // 사용 안 함 (항상 2라고 가정)

    size_t m = roads_rows;                 // 간선 수
    size_t totalEdges = 2 * m;             // 무방향이므로 2배

    // 1) 각 정점 차수 계산
    int *deg = (int*)calloc((size_t)n + 1, sizeof(int));
    if (!deg) return NULL;

    for (size_t i = 0; i < m; i++) {
        int a = roads[i][0];
        int b = roads[i][1];
        deg[a]++; deg[b]++;
    }

    // 2) CSR 형태의 인접 리스트 구성: start[v] ~ start[v+1]-1 구간이 v의 이웃
    int *start = (int*)malloc(((size_t)n + 2) * sizeof(int)); // start[1..n+1]
    if (!start) { free(deg); return NULL; }

    start[1] = 0;
    for (int v = 1; v <= n; v++) {
        start[v + 1] = start[v] + deg[v];
    }

    int *to = (int*)malloc(totalEdges * sizeof(int));
    if (!to) { free(start); free(deg); return NULL; }

    int *cur = (int*)malloc(((size_t)n + 1) * sizeof(int));
    if (!cur) { free(to); free(start); free(deg); return NULL; }

    for (int v = 1; v <= n; v++) cur[v] = start[v];

    for (size_t i = 0; i < m; i++) {
        int a = roads[i][0];
        int b = roads[i][1];
        to[cur[a]++] = b;
        to[cur[b]++] = a;
    }

    // 3) dist 배열 (-1 초기화)
    int *dist = (int*)malloc(((size_t)n + 1) * sizeof(int));
    if (!dist) { free(cur); free(to); free(start); free(deg); return NULL; }

    for (int v = 1; v <= n; v++) dist[v] = -1;
    dist[destination] = 0;

    // 4) BFS 큐
    int *q = (int*)malloc(((size_t)n) * sizeof(int));
    if (!q) { free(dist); free(cur); free(to); free(start); free(deg); return NULL; }

    int head = 0, tail = 0;
    q[tail++] = destination;

    while (head < tail) {
        int v = q[head++];
        int nd = dist[v] + 1;

        for (int idx = start[v]; idx < start[v + 1]; idx++) {
            int nxt = to[idx];
            if (dist[nxt] == -1) {
                dist[nxt] = nd;
                q[tail++] = nxt;
            }
        }
    }

    // 5) 정답 배열 생성
    int *answer = (int*)malloc(sources_len * sizeof(int));
    if (!answer) {
        free(q); free(dist); free(cur); free(to); free(start); free(deg);
        return NULL;
    }

    for (size_t i = 0; i < sources_len; i++) {
        answer[i] = dist[sources[i]]; // 도달 불가면 -1 그대로
    }

    // 6) 정리
    free(q);
    free(dist);
    free(cur);
    free(to);
    free(start);
    free(deg);

    return answer;
}