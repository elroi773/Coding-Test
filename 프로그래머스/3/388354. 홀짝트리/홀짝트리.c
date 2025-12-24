#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>

// 인접 리스트용 구조체
typedef struct Edge {
    int to;
    struct Edge* next;
} Edge;

int* solution(int nodes[], size_t nodes_len, int** edges,
              size_t edges_rows, size_t edges_cols) {

    // 노드 번호 최대값 (문제 조건)
    const int MAXV = 1000000;

    // 인접 리스트
    Edge** graph = (Edge**)calloc(MAXV + 1, sizeof(Edge*));
    int* degree = (int*)calloc(MAXV + 1, sizeof(int));
    bool* exists = (bool*)calloc(MAXV + 1, sizeof(bool));
    bool* visited = (bool*)calloc(MAXV + 1, sizeof(bool));

    // 노드 존재 표시
    for (size_t i = 0; i < nodes_len; i++) {
        exists[nodes[i]] = true;
    }

    // 간선 추가
    for (size_t i = 0; i < edges_rows; i++) {
        int a = edges[i][0];
        int b = edges[i][1];

        Edge* e1 = (Edge*)malloc(sizeof(Edge));
        e1->to = b;
        e1->next = graph[a];
        graph[a] = e1;

        Edge* e2 = (Edge*)malloc(sizeof(Edge));
        e2->to = a;
        e2->next = graph[b];
        graph[b] = e2;

        degree[a]++;
        degree[b]++;
    }

    int oddEvenTree = 0;
    int reverseTree = 0;

    // BFS용 큐
    int* queue = (int*)malloc(sizeof(int) * nodes_len);

    for (size_t i = 0; i < nodes_len; i++) {
        int start = nodes[i];
        if (visited[start]) continue;

        // BFS로 컴포넌트 수집
        int front = 0, back = 0;
        queue[back++] = start;
        visited[start] = true;

        int A = 0, B = 0;

        while (front < back) {
            int cur = queue[front++];

            if ((degree[cur] % 2) == (cur % 2))
                A++;
            else
                B++;

            for (Edge* e = graph[cur]; e; e = e->next) {
                if (!visited[e->to]) {
                    visited[e->to] = true;
                    queue[back++] = e->to;
                }
            }
        }

        if (A == 1) oddEvenTree++;
        if (B == 1) reverseTree++;
    }

    // 결과 반환
    int* answer = (int*)malloc(sizeof(int) * 2);
    answer[0] = oddEvenTree;
    answer[1] = reverseTree;

    return answer;
}
