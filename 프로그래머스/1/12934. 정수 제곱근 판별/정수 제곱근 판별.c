#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>   // sqrt 사용을 위해 필요

long long solution(long long n) {
    long long x = (long long)sqrt((double)n);  // 제곱근 구하기

    if (x * x == n) {  
        // n이 완전 제곱수일 경우
        return (x + 1) * (x + 1);
    } else {
        // 완전 제곱수가 아닐 경우
        return -1;
    }
}
