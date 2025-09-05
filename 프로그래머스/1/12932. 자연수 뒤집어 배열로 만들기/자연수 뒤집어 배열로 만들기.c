#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int* solution(long long n) {
    // 자릿수 구하기
    long long tmp = n;
    int count = 0;
    while (tmp > 0) {
        count++;
        tmp /= 10;
    }
    
    // 동적 할당
    int* answer = (int*)malloc(sizeof(int) * count);

    // 뒤집어서 저장
    for (int i = 0; i < count; i++) {
        answer[i] = n % 10;
        n /= 10;
    }

    return answer;  // 배열 포인터 반환
}

