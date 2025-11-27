#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long* solution(long long numbers[], size_t numbers_len) {
    // numbers_len 길이만큼 동적 할당
    long long* answer = (long long*)malloc(sizeof(long long) * numbers_len);
    
    for (size_t i = 0; i < numbers_len; i++) {
        long long x = numbers[i];
        
        if (x % 2 == 0) {
            // 짝수면 그냥 +1
            answer[i] = x + 1;
        } else {
            // 홀수면
            long long bit = 1;
            while (x & bit) {
                bit <<= 1; // 1이 아닌 첫 0 위치 찾기
            }
            // 그 자리 bit를 1로 만들고 바로 오른쪽 1비트만 0으로
            answer[i] = x + (bit >> 1);
        }
    }
    
    return answer;
}

// 테스트용 main
int main() {
    long long numbers[] = {2, 7};
    size_t len = sizeof(numbers) / sizeof(numbers[0]);
    
    long long* result = solution(numbers, len);
    
    for (size_t i = 0; i < len; i++) {
        printf("%lld ", result[i]);
    }
    
    free(result);
    return 0;
}
