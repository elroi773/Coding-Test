#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

static inline long long minll(long long a, long long b) { return a < b ? a : b; }

int solution(const char* numbers) {
    const long long INF = (long long)1e18;

    // digit -> (x,y) 좌표 (문제 그림과 동일한 3x4 배치)
    int x[10], y[10];
    x[0] = 1; y[0] = 3;
    x[1] = 0; y[1] = 0;
    x[2] = 1; y[2] = 0;
    x[3] = 2; y[3] = 0;
    x[4] = 0; y[4] = 1;
    x[5] = 1; y[5] = 1;
    x[6] = 2; y[6] = 1;
    x[7] = 0; y[7] = 2;
    x[8] = 1; y[8] = 2;
    x[9] = 2; y[9] = 2;

    // dist: a->b 이동 최단 비용(이동만), 같은 숫자는 0으로 두고 마지막에 cost로 변환
    long long dist[10][10];
    for (int i = 0; i < 10; i++) {
        for (int j = 0; j < 10; j++) dist[i][j] = (i == j) ? 0 : INF;
    }

    // 인접 간선 구성: 상하좌우=2, 대각=3
    for (int a = 0; a < 10; a++) {
        for (int b = a + 1; b < 10; b++) {
            int dx = x[a] - x[b]; if (dx < 0) dx = -dx;
            int dy = y[a] - y[b]; if (dy < 0) dy = -dy;
            if (dx <= 1 && dy <= 1 && !(dx == 0 && dy == 0)) {
                long long w = (dx == 0 || dy == 0) ? 2 : 3;
                dist[a][b] = dist[b][a] = w;
            }
        }
    }

    // Floyd-Warshall (10개라 매우 작음)
    for (int k = 0; k < 10; k++) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (dist[i][k] + dist[k][j] < dist[i][j]) {
                    dist[i][j] = dist[i][k] + dist[k][j];
                }
            }
        }
    }

    // cost[a][b] = a에서 b를 누르는 비용 (a==b면 제자리 누르기=1)
    int cost[10][10];
    for (int a = 0; a < 10; a++) {
        for (int b = 0; b < 10; b++) {
            if (a == b) cost[a][b] = 1;
            else cost[a][b] = (int)dist[a][b];
        }
    }

    // DP: dp[l][r] = 현재 왼손 l, 오른손 r (l!=r) 일 때 최소 비용
    long long dp[10][10], ndp[10][10];
    for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) dp[i][j] = INF;

    // 시작 위치: 왼손 4, 오른손 6
    dp[4][6] = 0;

    int n = (int)strlen(numbers);
    for (int idx = 0; idx < n; idx++) {
        int d = numbers[idx] - '0';

        for (int i = 0; i < 10; i++) for (int j = 0; j < 10; j++) ndp[i][j] = INF;

        for (int l = 0; l < 10; l++) {
            for (int r = 0; r < 10; r++) {
                if (l == r) continue;
                long long cur = dp[l][r];
                if (cur >= INF) continue;

                // 같은 숫자 위에 손가락이 있으면 반드시 그 손가락으로 눌러야 함
                if (d == l) {
                    ndp[l][r] = minll(ndp[l][r], cur + 1);
                } else if (d == r) {
                    ndp[l][r] = minll(ndp[l][r], cur + 1);
                } else {
                    // 왼손으로 누르기: (d, r)
                    if (d != r) {
                        ndp[d][r] = minll(ndp[d][r], cur + cost[l][d]);
                    }
                    // 오른손으로 누르기: (l, d)
                    if (d != l) {
                        ndp[l][d] = minll(ndp[l][d], cur + cost[r][d]);
                    }
                }
            }
        }

        memcpy(dp, ndp, sizeof(dp));
    }

    long long ans = INF;
    for (int l = 0; l < 10; l++) {
        for (int r = 0; r < 10; r++) {
            if (l == r) continue;
            ans = minll(ans, dp[l][r]);
        }
    }

    return (int)ans;
}