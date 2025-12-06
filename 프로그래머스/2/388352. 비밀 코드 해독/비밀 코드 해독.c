#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 전역 변수로 공유
static int g_n;
static int **g_q;
static size_t g_q_rows, g_q_cols;
static int *g_ans;
static size_t g_ans_len;
static int g_answer;
static int code[5];   // 현재 만들고 있는 비밀 코드 후보 (5개 정수)

// 두 포인터로 교집합 개수 계산 (code[5] 와 q_row[5]는 둘 다 오름차순)
static int count_intersection(int *code, int *q_row, size_t len_code, size_t len_q) {
    int i = 0, j = 0, cnt = 0;
    while (i < (int)len_code && j < (int)len_q) {
        if (code[i] == q_row[j]) {
            cnt++;
            i++;
            j++;
        } else if (code[i] < q_row[j]) {
            i++;
        } else {
            j++;
        }
    }
    return cnt;
}

// 깊이 우선 탐색으로 1~n 중 5개의 조합 생성
static void dfs(int idx, int start) {
    if (idx == 5) {
        // 현재 code[]가 모든 시도 조건을 만족하는지 검사
        for (size_t i = 0; i < g_q_rows; i++) {
            int cnt = count_intersection(code, g_q[i], 5, g_q_cols);
            if (cnt != g_ans[i]) {
                return; // 한 번이라도 불일치하면 이 코드는 탈락
            }
        }
        // 모든 조건 만족 → 유효한 비밀 코드
        g_answer++;
        return;
    }

    // 남은 자리를 채울 수 있는 범위까지만 반복
    // (n - (필요한 남은 개수) + 1) 까지
    for (int num = start; num <= g_n - (5 - idx) + 1; num++) {
        code[idx] = num;
        dfs(idx + 1, num + 1);
    }
}

// q_rows는 2차원 배열 q의 행 길이, q_cols는 2차원 배열 q의 열 길이입니다.
// ans_len은 배열 ans의 길이입니다.
int solution(int n, int** q, size_t q_rows, size_t q_cols, int ans[], size_t ans_len) {
    g_n = n;
    g_q = q;
    g_q_rows = q_rows;
    g_q_cols = q_cols;   // 문제 조건상 5
    g_ans = ans;
    g_ans_len = ans_len; // 문제 조건상 q_rows와 동일
    g_answer = 0;

    dfs(0, 1); // 1부터 시작해서 5개 뽑기

    return g_answer;
}