#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h> // sqrt 함수 사용

long long solution(int k, int d) {
    long long answer = 0;
    for (long long x = 0; x <= d; x += k) {
        long long max_y = (long long)sqrt((long long)d * d - x * x);
        answer += (max_y / k) + 1; // 0 포함이므로 +1
    }
    return answer;
}
