#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <string.h>

static int iabs(int a) { return a < 0 ? -a : a; }

char* solution(int n, int m, int x, int y, int r, int c, int k) {
    int dist = iabs(x - r) + iabs(y - c);

    // 불가능 조건: 거리 부족 or (남는 이동 수의 홀짝이 안 맞음)
    if (dist > k || ((k - dist) % 2 != 0)) {
        char *imp = (char*)malloc(11); // "impossible" + '\0'
        strcpy(imp, "impossible");
        return imp;
    }

    // 정답 문자열 할당
    char *ans = (char*)malloc((size_t)k + 1);
    int posx = x, posy = y;

    // 사전순(ASCII) 기준: d < l < r < u
    const char dirChar[4] = {'d', 'l', 'r', 'u'};
    const int dx[4] = {+1,  0,  0, -1};
    const int dy[4] = { 0, -1, +1,  0};

    for (int step = 0; step < k; step++) {
        bool moved = false;

        for (int i = 0; i < 4; i++) {
            int nx = posx + dx[i];
            int ny = posy + dy[i];

            // 격자 밖으로 나가면 안 됨
            if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

            int rem = k - (step + 1);
            int ndist = iabs(nx - r) + iabs(ny - c);

            // 남은 횟수로 도착 가능 + 홀짝 조건 만족해야 선택 가능
            if (ndist <= rem && ((rem - ndist) % 2 == 0)) {
                ans[step] = dirChar[i];
                posx = nx;
                posy = ny;
                moved = true;
                break;
            }
        }

        // 이론상 여기 오면 안 되지만(안전장치)
        if (!moved) {
            free(ans);
            char *imp = (char*)malloc(11);
            strcpy(imp, "impossible");
            return imp;
        }
    }

    ans[k] = '\0';
    return ans;
}