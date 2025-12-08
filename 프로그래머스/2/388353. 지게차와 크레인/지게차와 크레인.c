#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// storage_len은 배열 storage의 길이입니다.
// requests_len은 배열 requests의 길이입니다.
// 파라미터로 주어지는 문자열은 const로 주어집니다. 변경하려면 문자열을 복사해서 사용하세요.
int solution(const char* storage[], size_t storage_len, const char* requests[], size_t requests_len) {
    int n = (int)storage_len;          // 행 개수
    int m = (int)strlen(storage[0]);   // 열 개수 (모든 행 길이 동일)

    // 창고 상태를 복사해서 사용할 2차원 그리드 (최대 50x50)
    char grid[50][50];
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            grid[i][j] = storage[i][j];
        }
    }

    // 4방향 (상, 하, 좌, 우)
    int dr[4] = {-1, 1, 0, 0};
    int dc[4] = {0, 0, -1, 1};

    // 방문 배열 (확장 격자용, 최대 (50+2)x(50+2) = 52x52)
    bool visited[52][52];

    // BFS 큐
    int qr[52 * 52];
    int qc[52 * 52];

    for (size_t k = 0; k < requests_len; k++) {
        const char *req = requests[k];
        char target = req[0];

        // 크레인 요청 (길이 2): 해당 알파벳 전부 제거
        if (strlen(req) == 2) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (grid[i][j] == target) {
                        grid[i][j] = '.';  // 비워진 칸
                    }
                }
            }
            continue;
        }

        // 지게차 요청 (길이 1):
        // 1. 확장 격자에서 외부와 연결된 빈칸을 BFS로 탐색
        int H = n + 2;
        int W = m + 2;

        // visited 초기화
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                visited[i][j] = false;
            }
        }

        int head = 0, tail = 0;

        // (0,0)에서 시작: 완전히 바깥 영역
        visited[0][0] = true;
        qr[tail] = 0;
        qc[tail] = 0;
        tail++;

        while (head < tail) {
            int r = qr[head];
            int c = qc[head];
            head++;

            for (int d = 0; d < 4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc]) continue;

                // 확장 격자에서 실제 창고 영역인지 확인
                if (1 <= nr && nr <= n && 1 <= nc && nc <= m) {
                    // 실제 창고 안쪽: 빈 칸이면 이동 가능
                    if (grid[nr - 1][nc - 1] == '.') {
                        visited[nr][nc] = true;
                        qr[tail] = nr;
                        qc[tail] = nc;
                        tail++;
                    }
                } else {
                    // 창고 밖: 항상 이동 가능
                    visited[nr][nc] = true;
                    qr[tail] = nr;
                    qc[tail] = nc;
                    tail++;
                }
            }
        }

        // 2. 외부와 연결된 빈칸(또는 외부)에 인접한 target 컨테이너 제거
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] != target) continue;

                int er = i + 1; // 확장 격자 기준 좌표
                int ec = j + 1;
                bool removable = false;

                for (int d = 0; d < 4; d++) {
                    int nr = er + dr[d];
                    int nc = ec + dc[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                    if (visited[nr][nc]) {
                        removable = true;
                        break;
                    }
                }

                if (removable) {
                    grid[i][j] = '.';
                }
            }
        }
    }

    // 남은 컨테이너 개수 세기
    int answer = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
            if (grid[i][j] != '.') answer++;
        }
    }

    return answer;
}