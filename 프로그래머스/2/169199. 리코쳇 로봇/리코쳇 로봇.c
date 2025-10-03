#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int x, y, cnt;
} Node;

int dx[4] = {1, -1, 0, 0};
int dy[4] = {0, 0, 1, -1};

int solution(const char* board[], size_t board_len) {
    int n = board_len;
    int m = strlen(board[0]);
    int visited[101][101] = {0};

    int startX, startY;
    int goalX, goalY;

    // 시작점, 목표점 찾기
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (board[i][j] == 'R') {
                startX = i;
                startY = j;
            }
            if (board[i][j] == 'G') {
                goalX = i;
                goalY = j;
            }
        }
    }

    // BFS 큐
    Node queue[10001];
    int front = 0, rear = 0;

    queue[rear++] = (Node){startX, startY, 0};
    visited[startX][startY] = 1;

    while (front < rear) {
        Node cur = queue[front++];

        // 목표 도착
        if (cur.x == goalX && cur.y == goalY) {
            return cur.cnt;
        }

        // 4방향 이동
        for (int d = 0; d < 4; d++) {
            int nx = cur.x;
            int ny = cur.y;

            // 끝까지 미끄러짐
            while (1) {
                int tx = nx + dx[d];
                int ty = ny + dy[d];
                if (tx < 0 || ty < 0 || tx >= n || ty >= m) break;
                if (board[tx][ty] == 'D') break;
                nx = tx;
                ny = ty;
            }

            if (!visited[nx][ny]) {
                visited[nx][ny] = 1;
                queue[rear++] = (Node){nx, ny, cur.cnt + 1};
            }
        }
    }

    return -1; // 목표 도달 불가
}
