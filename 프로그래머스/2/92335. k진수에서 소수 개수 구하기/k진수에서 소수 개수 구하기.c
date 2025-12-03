#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// 소수 판별 함수 (long long 사용)
bool is_prime(long long x) {
    if (x < 2) return false;
    if (x == 2) return true;
    if (x % 2 == 0) return false;
    for (long long i = 3; i * i <= x; i += 2) {
        if (x % i == 0) return false;
    }
    return true;
}

int solution(int n, int k) {
    // 1. n을 k진수 문자열로 변환
    char buf[64];   // n <= 1,000,000 이라 이 정도로 충분히 넉넉
    int len = 0;
    int tmp = n;

    if (tmp == 0) {
        buf[len++] = '0';
    } else {
        while (tmp > 0) {
            int digit = tmp % k;
            buf[len++] = '0' + digit; // 0~9
            tmp /= k;
        }
    }

    // 현재 buf에는 뒤집힌 형태로 들어있으므로 뒤집기
    for (int i = 0; i < len / 2; i++) {
        char c = buf[i];
        buf[i] = buf[len - 1 - i];
        buf[len - 1 - i] = c;
    }

    // 2. '0'을 구분자로 보고 덩어리별로 숫자를 만들며 소수 카운트
    long long cur = 0; // 현재 덩어리를 십진수로 해석한 값
    int answer = 0;

    // 마지막까지 처리하기 위해 len까지 돌면서 마지막에 '0'을 한 번 더 준 효과를 냄
    for (int i = 0; i <= len; i++) {
        char c = (i < len) ? buf[i] : '0'; // sentinel '0'

        if (c == '0') {
            if (cur > 1) {        // 2 이상일 때만 소수 후보
                if (is_prime(cur)) {
                    answer++;
                }
            }
            cur = 0; // 덩어리 초기화
        } else {
            // 숫자 누적 (문자 '0'~'9' → 정수 0~9)
            cur = cur * 10 + (c - '0');
        }
    }

    return answer;
}