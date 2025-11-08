#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

#define MAX_N 101

int graph[MAX_N][MAX_N];   // 인접 행렬
bool visited[MAX_N];
int n_global;               // 노드 개수 전역 변수

// DFS로 연결된 송전탑 개수를 센다.
int dfs(int node) {
    visited[node] = true;
    int count = 1;

    for (int i = 1; i <= n_global; i++) {
        if (graph[node][i] == 1 && !visited[i]) {
            count += dfs(i);
        }
    }
    return count;
}

int solution(int n, int** wires, size_t wires_rows, size_t wires_cols) {
    n_global = n;
    int min_diff = n;

    // 인접 행렬 초기화
    memset(graph, 0, sizeof(graph));

    // 그래프 구성
    for (int i = 0; i < wires_rows; i++) {
        int a = wires[i][0];
        int b = wires[i][1];
        graph[a][b] = 1;
        graph[b][a] = 1;
    }

    // 모든 간선을 하나씩 끊어본다.
    for (int i = 0; i < wires_rows; i++) {
        int a = wires[i][0];
        int b = wires[i][1];

        // 간선 끊기
        graph[a][b] = graph[b][a] = 0;

        memset(visited, false, sizeof(visited));
        int countA = dfs(a);        // 한쪽 트리의 송전탑 개수
        int countB = n - countA;    // 나머지 트리의 송전탑 개수
        int diff = abs(countA - countB);

        if (diff < min_diff) min_diff = diff;

        // 간선 복원
        graph[a][b] = graph[b][a] = 1;
    }

    return min_diff;
}
