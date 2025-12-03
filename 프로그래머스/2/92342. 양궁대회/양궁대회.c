#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

static int n_arrows;        // 화살 개수
static int apeach[11];      // 어피치 info
static int cur[11];         // 현재 DFS에서의 라이언 배치
static int best[11];        // 최적 라이언 배치
static int best_diff;       // 최대 점수 차이 (라이언 - 어피치)

// DFS 함수: idx = 현재 점수 인덱스(0 -> 10), arrows_left = 남은 화살 수
void dfs(int idx, int arrows_left) {
    // 마지막 점수(0점, idx == 10) 처리
    if (idx == 10) {
        // 남은 화살 전부 0점에 사용
        cur[10] = arrows_left;

        int r_score = 0;
        int a_score = 0;

        // 점수 계산
        for (int i = 0; i < 11; i++) {
            if (apeach[i] == 0 && cur[i] == 0) continue;

            int point = 10 - i;
            if (cur[i] > apeach[i]) {
                r_score += point;
            } else {
                a_score += point;
            }
        }

        int diff = r_score - a_score;

        // 라이언이 이기지 못하면 무시
        if (diff <= 0) {
            cur[10] = 0; // 원상 복구
            return;
        }

        // 더 큰 점수 차이 발견 시 무조건 갱신
        if (diff > best_diff) {
            best_diff = diff;
            memcpy(best, cur, sizeof(best));
        }
        // 점수 차이가 동일하면, 낮은 점수(인덱스가 큰 쪽)를 더 많이 맞힌 쪽 선택
        else if (diff == best_diff) {
            for (int i = 10; i >= 0; i--) {
                if (cur[i] > best[i]) {
                    memcpy(best, cur, sizeof(best));
                    break;
                } else if (cur[i] < best[i]) {
                    break;
                }
            }
        }

        cur[10] = 0; // 원상 복구
        return;
    }

    // 1) 현재 점수(10 - idx)를 가져오기 위해 어피치보다 1발 더 쏘는 경우
    int need = apeach[idx] + 1;
    if (need <= arrows_left) {
        cur[idx] = need;
        dfs(idx + 1, arrows_left - need);
        cur[idx] = 0; // 백트래킹
    }

    // 2) 현재 점수 포기 (0발 쏘는 경우)
    dfs(idx + 1, arrows_left);
}

// info_len은 배열 info의 길이입니다.
int* solution(int n, int info[], size_t info_len) {
    // 전역 변수 초기화
    n_arrows = n;
    for (int i = 0; i < 11; i++) {
        apeach[i] = info[i];
        cur[i] = 0;
        best[i] = 0;
    }
    best_diff = -1; // 아직 이길 수 있는 경우 없음

    dfs(0, n_arrows);

    // 정답 배열 동적 할당 (프로그래머스에서는 보통 11로 고정)
    int* answer = (int*)malloc(sizeof(int) * 11);

    // 이길 수 있는 경우가 없는 경우
    if (best_diff <= 0) {
        answer[0] = -1;
        for (int i = 1; i < 11; i++) {
            answer[i] = 0;
        }
        return answer;
    }

    // 최적 배치 복사
    for (int i = 0; i < 11; i++) {
        answer[i] = best[i];
    }

    return answer;
}