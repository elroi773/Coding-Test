#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

int solution(int** info, size_t info_rows, size_t info_cols, int n, int m) {
    // INF 큰 값
    const int INF = INT_MAX / 4;

    // dp[b] = 최소 A 흔적으로 B 흔적이 b인 상태 (b from 0..m-1)
    // m >= 1 으로 가정 (제약)
    int *dp = (int*)malloc(sizeof(int) * m);
    if (!dp) return -1;
    for (int i = 0; i < m; ++i) dp[i] = INF;
    dp[0] = 0;

    for (size_t idx = 0; idx < info_rows; ++idx) {
        int a_cost = info[idx][0];
        int b_cost = info[idx][1];

        int *newdp = (int*)malloc(sizeof(int) * m);
        if (!newdp) { free(dp); return -1; }
        for (int i = 0; i < m; ++i) newdp[i] = INF;

        for (int b = 0; b < m; ++b) {
            if (dp[b] == INF) continue;

            // 1) A가 훔치는 경우: A 흔적 증가, B는 변하지 않음
            long long newA = (long long)dp[b] + a_cost;
            if (newA < n) { // A가 잡히지 않는 경우만 허용
                if (newA < newdp[b]) newdp[b] = (int)newA;
            }
            // 2) B가 훔치는 경우: B 흔적 증가, A는 변하지 않음
            int newB = b + b_cost;
            if (newB < m) { // B가 잡히지 않는 경우만 허용
                if (dp[b] < newdp[newB]) newdp[newB] = dp[b];
            }
        }

        free(dp);
        dp = newdp;
    }

    // 가능한 B 흔적 값들 중 최소 A 흔적 찾기
    int answer = INF;
    for (int b = 0; b < m; ++b) {
        if (dp[b] < answer) answer = dp[b];
    }
    free(dp);

    if (answer == INF) return -1;
    return answer;
}
