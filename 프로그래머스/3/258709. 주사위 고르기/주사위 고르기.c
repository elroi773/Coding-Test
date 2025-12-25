#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_SUM 600   // 최대 합 (100 * 6)
#define MAX_DICE 10

int n, half;
int bestWin = -1;
int bestPick[MAX_DICE];

void calc_distribution(int** dice, int* pick, int pickSize, int* dist) {
    int temp[MAX_SUM + 1] = {0};
    dist[0] = 1;

    for (int i = 0; i < pickSize; i++) {
        memset(temp, 0, sizeof(temp));
        for (int s = 0; s <= MAX_SUM; s++) {
            if (dist[s] == 0) continue;
            for (int f = 0; f < 6; f++) {
                temp[s + dice[pick[i]][f]] += dist[s];
            }
        }
        memcpy(dist, temp, sizeof(temp));
    }
}

void dfs(int** dice, int idx, int cnt, int* pick, int* used) {
    if (cnt == half) {
        int aDist[MAX_SUM + 1] = {0};
        int bDist[MAX_SUM + 1] = {0};
        int bPick[MAX_DICE], bCnt = 0;

        for (int i = 0; i < n; i++)
            if (!used[i]) bPick[bCnt++] = i;

        calc_distribution(dice, pick, half, aDist);
        calc_distribution(dice, bPick, half, bDist);

        // B 누적합
        int prefix[MAX_SUM + 2] = {0};
        for (int i = 0; i <= MAX_SUM; i++)
            prefix[i + 1] = prefix[i] + bDist[i];

        int win = 0;
        for (int a = 0; a <= MAX_SUM; a++) {
            if (aDist[a] == 0) continue;
            win += aDist[a] * prefix[a];
        }

        if (win > bestWin) {
            bestWin = win;
            memcpy(bestPick, pick, sizeof(int) * half);
        }
        return;
    }

    for (int i = idx; i < n; i++) {
        used[i] = 1;
        pick[cnt] = i;
        dfs(dice, i + 1, cnt + 1, pick, used);
        used[i] = 0;
    }
}

int* solution(int** dice, size_t dice_rows, size_t dice_cols) {
    n = dice_rows;
    half = n / 2;

    int pick[MAX_DICE];
    int used[MAX_DICE] = {0};

    dfs(dice, 0, 0, pick, used);

    int* answer = (int*)malloc(sizeof(int) * half);
    for (int i = 0; i < half; i++)
        answer[i] = bestPick[i] + 1; // 1-based index

    return answer;
}
