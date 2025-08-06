#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int num) {
    long long n = num;  // overflow 방지를 위해 long long 사용
    int count = 0;

    if (n == 1) return 0;

    while (n != 1 && count < 500) {
        if (n % 2 == 0) {
            n /= 2;
        } else {
            n = n * 3 + 1;
        }
        count++;
    }

    return (n == 1) ? count : -1;
}
