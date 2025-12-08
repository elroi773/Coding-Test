#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int* solution(int n) {
    int total = n * (n + 1) / 2;              // 채워야 할 총 숫자 개수
    int* answer = (int*)malloc(sizeof(int) * total);
    if (!answer) return NULL;

    // n x n 삼각 달팽이를 그릴 임시 배열 (전부 0으로 초기화)
    int* tri = (int*)calloc(n * n, sizeof(int));
    if (!tri) {
        free(answer);
        return NULL;
    }

    // 방향: 아래(0), 오른쪽(1), 왼쪽 위(2)
    int dr[3] = { 1, 0, -1 };
    int dc[3] = { 0, 1, -1 };

    int r = 0, c = 0, dir = 0;
    for (int num = 1; num <= total; num++) {
        tri[r * n + c] = num;

        int nr = r + dr[dir];
        int nc = c + dc[dir];

        // 범위를 벗어나거나 이미 채워진 칸이면 방향 전환
        if (nr < 0 || nr >= n || nc < 0 || nc >= n || tri[nr * n + nc] != 0) {
            dir = (dir + 1) % 3;
            nr = r + dr[dir];
            nc = c + dc[dir];
        }

        r = nr;
        c = nc;
    }

    // 삼각형 부분만 위에서부터 차례대로 answer에 복사
    int idx = 0;
    for (int i = 0; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            answer[idx++] = tri[i * n + j];
        }
    }

    free(tri);
    return answer;
}