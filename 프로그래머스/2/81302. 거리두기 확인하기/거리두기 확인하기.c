#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int check_place(const char* place[5]) {
    int dr[] = {-1, 1, 0, 0};
    int dc[] = {0, 0, -1, 1};

    for (int r = 0; r < 5; r++) {
        for (int c = 0; c < 5; c++) {
            if (place[r][c] != 'P') continue;

            // 상하좌우 바로 옆 체크 (거리 1)
            for (int k = 0; k < 4; k++) {
                int nr = r + dr[k];
                int nc = c + dc[k];
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                    if (place[nr][nc] == 'P') return 0;
                }
            }

            // 거리 2 체크
            int dr2[] = {-2, 2, 0, 0};
            int dc2[] = {0, 0, -2, 2};
            for (int k = 0; k < 4; k++) {
                int nr = r + dr2[k];
                int nc = c + dc2[k];
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                    // 중간 위치가 파티션인지 확인
                    int mr = (r + nr) / 2;
                    int mc = (c + nc) / 2;
                    if (place[nr][nc] == 'P' && place[mr][mc] != 'X') return 0;
                }
            }

            // 대각선 체크
            int dr_diag[] = {-1, -1, 1, 1};
            int dc_diag[] = {-1, 1, -1, 1};
            for (int k = 0; k < 4; k++) {
                int nr = r + dr_diag[k];
                int nc = c + dc_diag[k];
                if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                    if (place[nr][nc] == 'P') {
                        if (!(place[r][nc] == 'X' && place[nr][c] == 'X')) return 0;
                    }
                }
            }
        }
    }
    return 1;
}

int* solution(const char*** places, size_t places_rows, size_t places_cols) {
    int* answer = (int*)malloc(sizeof(int) * 5);

    for (int i = 0; i < 5; i++) {
        answer[i] = check_place(places[i]);
    }

    return answer;
}