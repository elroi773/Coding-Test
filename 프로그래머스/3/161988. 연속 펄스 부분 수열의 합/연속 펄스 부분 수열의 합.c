#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// sequence_len은 배열 sequence의 길이입니다.
long long solution(int sequence[], size_t sequence_len) {
    long long max_sum = 0;

    long long curr1 = 0; // [1, -1, 1, -1 ...]
    long long curr2 = 0; // [-1, 1, -1, 1 ...]

    for (size_t i = 0; i < sequence_len; i++) {
        long long sign1 = (i % 2 == 0) ? 1 : -1;
        long long sign2 = -sign1;

        long long v1 = sequence[i] * sign1;
        long long v2 = sequence[i] * sign2;

        curr1 = (curr1 + v1 > v1) ? curr1 + v1 : v1;
        curr2 = (curr2 + v2 > v2) ? curr2 + v2 : v2;

        if (curr1 > max_sum) max_sum = curr1;
        if (curr2 > max_sum) max_sum = curr2;
    }

    return max_sum;
}
