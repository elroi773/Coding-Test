#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define MAX 1000000

typedef struct {
    int value;
    int count;
} Node;

int solution(int x, int y, int n) {
    if (x == y) return 0;

    bool visited[MAX + 1] = { false };
    Node queue[MAX + 1];
    int front = 0, rear = 0;

    queue[rear++] = (Node){x, 0};
    visited[x] = true;

    while (front < rear) {
        Node cur = queue[front++];
        int val = cur.value;
        int cnt = cur.count;

        // 다음 가능한 연산
        int nexts[3] = { val + n, val * 2, val * 3 };

        for (int i = 0; i < 3; i++) {
            int next = nexts[i];
            if (next > y || visited[next]) continue;

            if (next == y) return cnt + 1;

            visited[next] = true;
            queue[rear++] = (Node){next, cnt + 1};
        }
    }

    return -1; // 도달 불가
}
