#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// points_rows는 2차원 배열 points의 행 길이, points_cols는 2차원 배열 points의 열 길이입니다.
// routes_rows는 2차원 배열 routes의 행 길이, routes_cols는 2차원 배열 routes의 열 길이입니다.
int solution(int** points, size_t points_rows, size_t points_cols,
             int** routes, size_t routes_rows, size_t routes_cols) {
    int answer = 0;

    size_t x = routes_rows;      // 로봇 수
    size_t n = points_rows;      // 포인트 수 (사용은 안 하지만 의미상 둠)

    // 로봇 상태 배열 동적 할당
    int* rs = (int*)malloc(sizeof(int) * x);       // 현재 r
    int* cs = (int*)malloc(sizeof(int) * x);       // 현재 c
    int* seg_idx = (int*)malloc(sizeof(int) * x);  // 현재 도착 포인트 인덱스 (1 ~ routes_cols-1)
    int* active = (int*)malloc(sizeof(int) * x);   // 1: 센터 안, 0: 센터 밖

    // 시작 상태 설정
    for (size_t i = 0; i < x; i++) {
        int start_point_id = routes[i][0] - 1;  // 0-based
        rs[i] = points[start_point_id][0];
        cs[i] = points[start_point_id][1];
        seg_idx[i] = 1;   // routes[i][0] -> routes[i][1] 구간으로 시작
        active[i] = 1;
    }

    // 좌표 범위: 1~100 이므로 101x101 배열 사용 (0 인덱스는 사용 안 함)
    static int visit[101][101];
    static int cnt[101][101];
    int tick = 1;   // 방문 버전 관리용

    while (1) {
        int any_active = 0;
        int collisions_this_time = 0;

        // 1) 현재 시각의 각 로봇 위치로 충돌 여부 계산
        for (size_t i = 0; i < x; i++) {
            if (!active[i]) continue;

            any_active = 1;
            int r = rs[i];
            int c = cs[i];

            // 해당 좌표를 이번 tick에 처음 방문하면 초기화
            if (visit[r][c] != tick) {
                visit[r][c] = tick;
                cnt[r][c] = 1;
            } else {
                cnt[r][c]++;
                // 1 -> 2로 올라갈 때만 "위험 1회" 추가
                if (cnt[r][c] == 2) {
                    collisions_this_time++;
                }
            }
        }

        // 더 이상 센터 안에 로봇이 없으면 종료
        if (!any_active) break;

        // 이 시각의 위험 횟수 합산
        answer += collisions_this_time;

        // 2) 다음 시각으로 각 로봇 이동
        for (size_t i = 0; i < x; i++) {
            if (!active[i]) continue;

            int m = (int)routes_cols;
            int idx = seg_idx[i];            // 현재 도착 포인트 인덱스 (1 ~ m-1)

            // 안전 장치 (이론상 idx >= m이면 이미 끝난 상태)
            if (idx >= m) {
                active[i] = 0;
                continue;
            }

            int target_id = routes[i][idx] - 1;
            int tr = points[target_id][0];
            int tc = points[target_id][1];

            // 지금 이미 목표 포인트에 서 있는 경우
            if (rs[i] == tr && cs[i] == tc) {
                if (idx == m - 1) {
                    // 마지막 포인트에 도착 → 다음 시각부터 센터 밖
                    active[i] = 0;
                    continue;
                } else {
                    // 다음 구간으로 넘어감
                    idx++;
                    seg_idx[i] = idx;
                    target_id = routes[i][idx] - 1;
                    tr = points[target_id][0];
                    tc = points[target_id][1];
                }
            }

            // 목표 좌표를 향해 r 우선 한 칸 이동
            if (rs[i] != tr) {
                if (tr > rs[i]) rs[i]++;
                else rs[i]--;
            } else if (cs[i] != tc) {
                if (tc > cs[i]) cs[i]++;
                else cs[i]--;
            }
            // 둘 다 같을 일은 위에서 처리
        }

        tick++;  // 다음 시간 tick
    }

    free(rs);
    free(cs);
    free(seg_idx);
    free(active);

    return answer;
}