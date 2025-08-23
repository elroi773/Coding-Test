#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

int solution(int n) {
    for (int x = 1; x < n; x++) {
        if (n % x == 1) {
            return x;
        }
    }
    return -1; // 여기까지 오지 않지만 안전하게 추가
}

int main() {
    printf("%d\n", solution(10)); // 3
    printf("%d\n", solution(12)); // 11
    return 0;
}
