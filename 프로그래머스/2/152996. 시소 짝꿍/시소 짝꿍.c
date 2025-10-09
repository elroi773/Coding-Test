#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long solution(int weights[], size_t weights_len) {
    long long answer = 0;
    long long count[1001] = {0};  // 몸무게별 개수 카운트
    
    // 1️⃣ 각 몸무게 개수 세기
    for (size_t i = 0; i < weights_len; i++) {
        count[weights[i]]++;
    }

    // 2️⃣ 가능한 비율 쌍 (분수 형태)
    int numerators[4] = {1, 3, 2, 4};
    int denominators[4] = {1, 2, 1, 3};

    // 3️⃣ 모든 몸무게 w에 대해 가능한 짝 찾기
    for (int w = 100; w <= 1000; w++) {
        if (count[w] == 0) continue;

        // 같은 몸무게끼리 짝꿍 (조합 nC2)
        if (count[w] > 1) {
            answer += (count[w] * (count[w] - 1)) / 2;
        }

        // 다른 무게끼리 짝꿍
        for (int i = 1; i < 4; i++) {  // 비율 1 제외 (이미 처리됨)
            int num = numerators[i];
            int den = denominators[i];

            // w * num / den 이 정수가 될 때만 확인
            if ((w * num) % den != 0) continue;
            int target = (w * num) / den;

            if (target > 1000) continue;  // 범위 벗어나면 제외
            if (count[target] > 0) {
                answer += count[w] * count[target];
            }
        }
    }

    return answer;
}

