#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// park_len = 세로(H), park[i] 길이 = 가로(W)
int* solution(const char* park[], size_t park_len, const char* routes[], size_t routes_len) {
    int H = park_len;
    int W = strlen(park[0]);

    // 시작 위치 찾기
    int r = 0, c = 0;
    for (int i = 0; i < H; i++) {
        for (int j = 0; j < W; j++) {
            if (park[i][j] == 'S') {
                r = i; c = j;
            }
        }
    }

    // 방향 정의 (N, S, W, E)
    int dr[128], dc[128];
    dr['N'] = -1; dc['N'] = 0;
    dr['S'] =  1; dc['S'] = 0;
    dr['W'] =  0; dc['W'] = -1;
    dr['E'] =  0; dc['E'] =  1;

    // 명령 실행
    for (size_t k = 0; k < routes_len; k++) {
        char dir;
        int dist;
        sscanf(routes[k], "%c %d", &dir, &dist);

        int nr = r, nc = c;
        bool valid = true;

        for (int step = 1; step <= dist; step++) {
            int tr = r + dr[dir] * step;
            int tc = c + dc[dir] * step;

            // 범위 체크
            if (tr < 0 || tr >= H || tc < 0 || tc >= W) {
                valid = false;
                break;
            }
            // 장애물 체크
            if (park[tr][tc] == 'X') {
                valid = false;
                break;
            }
            nr = tr;
            nc = tc;
        }

        if (valid) { // 이동 가능하면 위치 업데이트
            r = nr;
            c = nc;
        }
    }

    int* answer = (int*)malloc(sizeof(int) * 2);
    answer[0] = r;
    answer[1] = c;
    return answer;
}
