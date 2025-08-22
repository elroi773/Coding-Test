#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <math.h>

// 소수 판별 함수
bool isPrime(int n) {
    if (n < 2) return false;
    for (int i = 2; i <= (int)sqrt(n); i++) {
        if (n % i == 0) return false;
    }
    return true;
}

// nums_len은 배열 nums의 길이입니다.
int solution(int nums[], size_t nums_len) {
    int answer = 0;

    // 3중 for문으로 조합 생성
    for (size_t i = 0; i < nums_len - 2; i++) {
        for (size_t j = i + 1; j < nums_len - 1; j++) {
            for (size_t k = j + 1; k < nums_len; k++) {
                int sum = nums[i] + nums[j] + nums[k];
                if (isPrime(sum)) {
                    answer++;
                }
            }
        }
    }

    return answer;
}
