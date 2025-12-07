#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 특정 영역이 모두 같은 값인지 검사하는 함수
bool isSame(int** arr, int x, int y, int size) {
    int value = arr[x][y];
    for (int i = x; i < x + size; i++) {
        for (int j = y; j < y + size; j++) {
            if (arr[i][j] != value) return false;
        }
    }
    return true;
}

// 쿼드트리 압축 수행
void compress(int** arr, int x, int y, int size, int* answer) {
    // 영역이 모두 같은 값이면 압축 가능 → 해당 값 count++
    if (isSame(arr, x, y, size)) {
        answer[arr[x][y]]++;
        return;
    }

    // 아니면 4개로 쪼개서 재귀 호출
    int newSize = size / 2;

    compress(arr, x, y, newSize, answer);                      // 좌상
    compress(arr, x, y + newSize, newSize, answer);            // 우상
    compress(arr, x + newSize, y, newSize, answer);            // 좌하
    compress(arr, x + newSize, y + newSize, newSize, answer);  // 우하
}

int* solution(int** arr, size_t arr_rows, size_t arr_cols) {
    int* answer = (int*)malloc(sizeof(int) * 2);
    answer[0] = 0;  // 0의 개수
    answer[1] = 0;  // 1의 개수

    compress(arr, 0, 0, arr_rows, answer);

    return answer;
}
