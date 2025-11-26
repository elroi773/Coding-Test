#include <stdio.h>
#include <string.h>

int solution(const char* want[], size_t want_len, int number[], size_t number_len, const char* discount[], size_t discount_len) {
    int answer = 0;

    // 1. discount 배열에서 10일씩 확인
    for (size_t i = 0; i + 10 <= discount_len; i++) {
        int count[10] = {0}; // want_len <= 10
        // 10일 동안 각 상품 수량 카운트
        for (size_t j = i; j < i + 10; j++) {
            for (size_t k = 0; k < want_len; k++) {
                if (strcmp(discount[j], want[k]) == 0) {
                    count[k]++;
                    break;
                }
            }
        }

        // 2. 원하는 수량과 비교
        int match = 1;
        for (size_t k = 0; k < want_len; k++) {
            if (count[k] != number[k]) {
                match = 0;
                break;
            }
        }

        if (match) {
            answer++;
        }
    }

    return answer;
}
