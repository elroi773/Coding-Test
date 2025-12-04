#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long gcd(long long a, long long b) {
    while (b != 0) {
        long long r = a % b;
        a = b;
        b = r;
    }
    return a;
}

long long solution(int w, int h) {
    long long W = (long long)w;
    long long H = (long long)h;

    long long g = gcd(W, H);
    // 전체 정사각형 개수 - 대각선이 지나가 망가지는 정사각형 개수
    // 망가지는 개수 = W + H - gcd(W, H)
    long long answer = W * H - (W + H - g);

    return answer;
}