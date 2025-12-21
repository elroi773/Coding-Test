#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 최대공약수 (유클리드 호제법)
int gcd(int a, int b) {
    while (b != 0) {
        int temp = a % b;
        a = b;
        b = temp;
    }
    return a;
}

// arr_len은 배열 arr의 길이입니다.
int solution(int arr[], size_t arr_len) {
    int lcm = arr[0];

    for (size_t i = 1; i < arr_len; i++) {
        lcm = lcm * arr[i] / gcd(lcm, arr[i]);
    }

    return lcm;
}
