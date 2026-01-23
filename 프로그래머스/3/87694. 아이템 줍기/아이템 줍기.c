#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define MAX 102  // 1~50을 2배 => 2~100, 여유 포함

typedef struct {
    int x, y, d;
} Node;

int solution(int **rectangle, size_t rectangle_row_len, size_t rectangle_col_len,
             int characterX, int characterY, int itemX, int itemY) {
    (void)rectangle_col_len; // 항상 4라 가정, 경고 방지용

    static int board[MAX][MAX];
    static bool visited[MAX][MAX];

    // 초기화
    for (int i = 0; i < MAX; i++) {
        for (int j = 0; j < MAX; j++) {
            board[i][j] = 0;
            visited[i][j] = false;
        }
    }

    // 1) 모든 직사각형 영역을 1로 채우기 (겹치는 곳도 1)
    for (size_t i = 0; i < rectangle_row_len; i++) {
        int x1 = rectangle[i][0] * 2;
        int y1 = rectangle[i][1] * 2;
        int x2 = rectangle[i][2] * 2;
        int y2 = rectangle[i][3] * 2;

        for (int x = x1; x <= x2; x++) {
            for (int y = y1; y <= y2; y++) {
                board[x][y] = 1;
            }
        }
    }

    // 2) 각 직사각형 내부를 0으로 지우기 (테두리만 남김)
    for (size_t i = 0; i < rectangle_row_len; i++) {
        int x1 = rectangle[i][0] * 2;
        int y1 = rectangle[i][1] * 2;
        int x2 = rectangle[i][2] * 2;
        int y2 = rectangle[i][3] * 2;

        for (int x = x1 + 1; x <= x2 - 1; x++) {
            for (int y = y1 + 1; y <= y2 - 1; y++) {
                board[x][y] = 0;
            }
        }
    }

    // 3) BFS (테두리 board==1 만 이동)
    int sx = characterX * 2;
    int sy = characterY * 2;
    int tx = itemX * 2;
    int ty = itemY * 2;

    static Node q[MAX * MAX];
    int head = 0, tail = 0;

    q[tail++] = (Node){sx, sy, 0};
    visited[sx][sy] = true;

    const int dx[4] = {1, -1, 0, 0};
    const int dy[4] = {0, 0, 1, -1};

    while (head < tail) {
        Node cur = q[head++];

        if (cur.x == tx && cur.y == ty) {
            return cur.d / 2; // 2배 좌표에서의 거리 -> 원래 거리
        }

        for (int dir = 0; dir < 4; dir++) {
            int nx = cur.x + dx[dir];
            int ny = cur.y + dy[dir];

            if (nx < 0 || ny < 0 || nx >= MAX || ny >= MAX) continue;
            if (visited[nx][ny]) continue;
            if (board[nx][ny] != 1) continue; // 테두리만

            visited[nx][ny] = true;
            q[tail++] = (Node){nx, ny, cur.d + 1};
        }
    }

    return 0; // 문제 조건상 도달 불가 케이스 없음
}