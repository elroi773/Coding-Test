#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int* solution(int target) {
    const int INF = 1000000000;

    // (score, singleOrBullCount)
    int scores[61];
    int scnt[61];
    int k = 0;

    // 1~20: single/double/triple
    for (int i = 1; i <= 20; i++) {
        scores[k] = i;      scnt[k] = 1; k++; // single
        scores[k] = i * 2;  scnt[k] = 0; k++; // double
        scores[k] = i * 3;  scnt[k] = 0; k++; // triple
    }
    // bull
    scores[k] = 50; scnt[k] = 1; k++;

    int* dpDarts = (int*)malloc((target + 1) * sizeof(int));
    int* dpSingles = (int*)malloc((target + 1) * sizeof(int));

    for (int t = 0; t <= target; t++) {
        dpDarts[t] = INF;
        dpSingles[t] = -INF;
    }
    dpDarts[0] = 0;
    dpSingles[0] = 0;

    for (int t = 1; t <= target; t++) {
        for (int i = 0; i < k; i++) {
            int s = scores[i];
            if (t >= s && dpDarts[t - s] != INF) {
                int candDarts = dpDarts[t - s] + 1;
                int candSingles = dpSingles[t - s] + scnt[i];

                if (candDarts < dpDarts[t] ||
                    (candDarts == dpDarts[t] && candSingles > dpSingles[t])) {
                    dpDarts[t] = candDarts;
                    dpSingles[t] = candSingles;
                }
            }
        }
    }

    int* answer = (int*)malloc(2 * sizeof(int));
    answer[0] = dpDarts[target];
    answer[1] = dpSingles[target];

    free(dpDarts);
    free(dpSingles);

    return answer;
}