#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <limits.h>

// onboard_len은 배열 onboard의 길이입니다.
int solution(int temperature, int t1, int t2, int a, int b, int onboard[], size_t onboard_len) {
    const int SHIFT = 10;          // -10 -> 0, 40 -> 50
    const int MIN_T = -10, MAX_T = 40;
    const int STATES = 51;
    const long long INF = (1LL << 60);

    int lo = t1 + SHIFT;
    int hi = t2 + SHIFT;

    long long dp[STATES], ndp[STATES];

    for (int i = 0; i < STATES; i++) dp[i] = INF;
    dp[temperature + SHIFT] = 0;   // 0분 실내온도 = 실외온도

    for (size_t i = 0; i + 1 < onboard_len; i++) {
        // i분에 승객 탑승이면 i분 시점 온도는 [t1, t2]여야 함
        if (onboard[i] == 1) {
            for (int t = 0; t < STATES; t++) {
                if (t < lo || t > hi) dp[t] = INF;
            }
        }

        for (int t = 0; t < STATES; t++) ndp[t] = INF;

        for (int idx = 0; idx < STATES; idx++) {
            long long cost = dp[idx];
            if (cost >= INF) continue;

            int cur = idx - SHIFT;

            // 1) 에어컨 OFF: 실외온도 방향으로 1도 이동(또는 유지), 비용 0
            int nxt = cur;
            if (cur < temperature) nxt = cur + 1;
            else if (cur > temperature) nxt = cur - 1;
            int nidx = nxt + SHIFT;
            if (cost < ndp[nidx]) ndp[nidx] = cost;

            // 2) 에어컨 ON & 희망온도 = 현재온도: 유지, 비용 b
            if (cost + b < ndp[idx]) ndp[idx] = cost + b;

            // 3) 에어컨 ON & 희망온도 > 현재온도: +1, 비용 a
            if (cur < MAX_T) {
                if (cost + a < ndp[idx + 1]) ndp[idx + 1] = cost + a;
            }

            // 4) 에어컨 ON & 희망온도 < 현재온도: -1, 비용 a
            if (cur > MIN_T) {
                if (cost + a < ndp[idx - 1]) ndp[idx - 1] = cost + a;
            }
        }

        for (int t = 0; t < STATES; t++) dp[t] = ndp[t];
    }

    // 마지막 시점(onboard_len-1분)도 탑승이면 온도 제한 적용
    if (onboard[onboard_len - 1] == 1) {
        for (int t = 0; t < STATES; t++) {
            if (t < lo || t > hi) dp[t] = INF;
        }
    }

    long long ans = INF;
    for (int t = 0; t < STATES; t++) {
        if (dp[t] < ans) ans = dp[t];
    }
    return (int)ans;
}