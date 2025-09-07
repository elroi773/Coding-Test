#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

bool solution(int x) {
    int sum = 0;
    int n = x;

    // 자릿수 합 구하기
    while (n > 0) {
        sum += n % 10;  // 마지막 자리 더하기
        n /= 10;        // 다음 자리로 이동
    }

    // 하샤드 수 판별
    if (x % sum == 0) {
        return true;
    } else {
        return false;
    }
}
