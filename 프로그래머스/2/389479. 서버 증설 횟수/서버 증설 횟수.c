#include <stdio.h>

int solution(int players[], int len, int m, int k) {
    int server[len];
    for (int i = 0; i < len; i++) server[i] = 0;
    int serverCnt = 0;

    for (int i = 0; i < len; i++) {
        int n = 0;
        if (players[i] >= m) {
            n = players[i] / m;

            int addServerCnt = 1;
            if (server[i] < n) {
                addServerCnt = n - server[i];
                if (n * m <= players[i] && players[i] < (n + 1) * m) {
                    serverCnt += addServerCnt;
                    for (int j = 0; j < k; j++) {
                        if (i + j < len) {
                            server[i + j] += addServerCnt;
                        } else break;
                    }
                }
            }
        }
    }
    return serverCnt;
}
