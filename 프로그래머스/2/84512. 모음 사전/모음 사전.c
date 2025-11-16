#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int solution(const char* word) {
    // 알파벳 순서: A, E, I, O, U
    char vowels[] = {'A', 'E', 'I', 'O', 'U'};
    // 자리수별 가중치
    int weights[] = {781, 156, 31, 6, 1};

    int answer = 0;
    int len = strlen(word);

    for (int i = 0; i < len; i++) {
        // 현재 문자가 vowels 배열에서 몇번째에 있는지 찾음
        int index = 0;
        for (int j = 0; j < 5; j++) {
            if (word[i] == vowels[j]) {
                index = j;
                break;
            }
        }
        // 해당 자리의 기여도 계산 (index * weight + 1)
        answer += index * weights[i] + 1;
    }

    return answer;
}
