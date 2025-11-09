#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

#define MAX_TOPPING 10001  // 문제 조건: 1 ≤ topping[i] ≤ 10,000

int solution(int topping[], size_t topping_len) {
    int answer = 0;
    int *left = (int *)malloc(sizeof(int) * topping_len);
    int *right = (int *)malloc(sizeof(int) * topping_len);
    
    bool seen_left[MAX_TOPPING] = {false};
    bool seen_right[MAX_TOPPING] = {false};
    
    int count_left = 0;
    int count_right = 0;

    // 1️⃣ 왼쪽 누적 (0 ~ i까지 서로 다른 토핑 수)
    for (int i = 0; i < topping_len; i++) {
        if (!seen_left[topping[i]]) {
            seen_left[topping[i]] = true;
            count_left++;
        }
        left[i] = count_left;
    }

    // 2️⃣ 오른쪽 누적 (i ~ 끝까지 서로 다른 토핑 수)
    for (int i = topping_len - 1; i >= 0; i--) {
        if (!seen_right[topping[i]]) {
            seen_right[topping[i]] = true;
            count_right++;
        }
        right[i] = count_right;
    }

    // 3️⃣ 자를 위치별 비교
    for (int i = 0; i < topping_len - 1; i++) {
        if (left[i] == right[i + 1]) {
            answer++;
        }
    }

    free(left);
    free(right);

    return answer;
}
