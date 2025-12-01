#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int discountRate[4] = {10, 20, 30, 40};   // 가능한 할인율
int bestSubscribe = 0;
int bestSales = 0;

int users_copy[100][2];  // [비율, 가격]
int emo_copy[10];
int discounts[10];       // 현재 할인 조합
int n_users, n_emoticons;

void dfs(int idx) {
    // 할인율을 다 정했다면 계산
    if (idx == n_emoticons) {
        int subscribeCnt = 0;
        int sales = 0;

        // 사용자 하나씩 처리
        for (int u = 0; u < n_users; u++) {
            int userRate = users_copy[u][0];   // 최소 할인 비율
            int limit = users_copy[u][1];      // 구매 상한 가격
            int sum = 0;

            // 이모티콘 구매 금액 계산
            for (int e = 0; e < n_emoticons; e++) {
                if (discounts[e] >= userRate) {
                    sum += emo_copy[e] * (100 - discounts[e]) / 100;
                }
            }

            // 구매 금액이 한도 이상이면 서비스 가입
            if (sum >= limit) {
                subscribeCnt++;
            } else {
                sales += sum;
            }
        }

        // 최대 가입자 > 최대 판매액 우선 비교
        if (subscribeCnt > bestSubscribe ||
           (subscribeCnt == bestSubscribe && sales > bestSales)) {
            bestSubscribe = subscribeCnt;
            bestSales = sales;
        }

        return;
    }

    // 10%, 20%, 30%, 40% 할인 모두 시도
    for (int i = 0; i < 4; i++) {
        discounts[idx] = discountRate[i];
        dfs(idx + 1);
    }
}

int* solution(int** users, size_t users_rows, size_t users_cols,
              int emoticons[], size_t emoticons_len) {

    // 전역 변수에 데이터 저장
    n_users = users_rows;
    n_emoticons = emoticons_len;

    for (int i = 0; i < n_users; i++) {
        users_copy[i][0] = users[i][0];  // 비율
        users_copy[i][1] = users[i][1];  // 가격
    }

    for (int i = 0; i < n_emoticons; i++) {
        emo_copy[i] = emoticons[i];
    }

    // DFS로 모든 할인 조합 탐색
    dfs(0);

    // 결과 반환
    int* answer = (int*)malloc(sizeof(int) * 2);
    answer[0] = bestSubscribe;
    answer[1] = bestSales;

    return answer;
}
