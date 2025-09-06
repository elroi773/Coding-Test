#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// sizes_rows는 2차원 배열 sizes의 행 길이, sizes_cols는 열 길이입니다.
int solution(int** sizes, size_t sizes_rows, size_t sizes_cols) {
    int max_w = 0; 
    int max_h = 0;
    
    for (size_t i = 0; i < sizes_rows; i++) {
        int w = sizes[i][0];
        int h = sizes[i][1];

        // 항상 큰 쪽을 w, 작은 쪽을 h로
        if (w < h) {
            int tmp = w;
            w = h;
            h = tmp;
        }

        if (w > max_w) max_w = w;
        if (h > max_h) max_h = h;
    }

    return max_w * max_h;
}
