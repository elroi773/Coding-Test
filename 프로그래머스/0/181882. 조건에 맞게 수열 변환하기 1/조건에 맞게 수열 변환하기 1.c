#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// arr_len은 배열 arr의 길이입니다.
int* solution(int arr[], size_t arr_len) {
    // 배열 길이만큼 메모리 할당
    int* answer = (int*)malloc(sizeof(int) * arr_len);
    
    for (size_t i = 0; i < arr_len; i++) {
        if (arr[i] >= 50 && arr[i] % 2 == 0) {
            answer[i] = arr[i] / 2;
        } else if (arr[i] < 50 && arr[i] % 2 == 1) {
            answer[i] = arr[i] * 2;
        } else {
            answer[i] = arr[i];
        }
    }
    
    return answer;
}
