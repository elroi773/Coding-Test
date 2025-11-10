#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 방향 정의: 상, 우, 하, 좌
int dx[4] = {-1, 0, 1, 0};
int dy[4] = {0, 1, 0, -1};

// qsort용 비교 함수
int cmp(const void* a, const void* b) {
    return (*(int*)a - *(int*)b);
}

int* solution(const char* grid[], size_t grid_len) {
    int n = grid_len;
    int m = strlen(grid[0]);

    // visited[x][y][dir]
    bool*** visited = (bool***)malloc(sizeof(bool**) * n);
    for (int i = 0; i < n; i++) {
        visited[i] = (bool**)malloc(sizeof(bool*) * m);
        for (int j = 0; j < m; j++) {
            visited[i][j] = (bool*)calloc(4, sizeof(bool));
        }
    }

    int* answer = (int*)malloc(sizeof(int) * n * m * 4);
    int answerCount = 0;

    // 모든 칸, 모든 방향에서 시도
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            for (int d = 0; d < 4; d++) {
                if (!visited[i][j][d]) {
                    int x = i, y = j, dir = d, cnt = 0;

                    // 사이클 추적
                    while (!visited[x][y][dir]) {
                        visited[x][y][dir] = true;
                        cnt++;

                        // 다음 위치 계산 (토러스)
                        x = (x + dx[dir] + n) % n;
                        y = (y + dy[dir] + m) % m;

                        // 방향 전환
                        if (grid[x][y] == 'L') {
                            dir = (dir + 3) % 4;
                        } else if (grid[x][y] == 'R') {
                            dir = (dir + 1) % 4;
                        }
                    }
                    answer[answerCount++] = cnt;
                }
            }
        }
    }

    // 정렬
    qsort(answer, answerCount, sizeof(int), cmp);

    // 필요한 길이만큼 리턴 배열 리사이즈
    answer = realloc(answer, sizeof(int) * answerCount);

    // 메모리 해제
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            free(visited[i][j]);
        }
        free(visited[i]);
    }
    free(visited);

    // 문제에서 배열 길이를 알 수 없기 때문에,
    // 실제 테스트에서는 반환 후 전역 변수 등으로 길이를 별도로 전달해야 함.
    return answer;
}
