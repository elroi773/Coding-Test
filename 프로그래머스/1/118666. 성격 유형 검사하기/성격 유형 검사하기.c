#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// 점수 계산 (선택지 → 점수)
int get_score(int choice) {
    if (choice == 1 || choice == 7) return 3;
    if (choice == 2 || choice == 6) return 2;
    if (choice == 3 || choice == 5) return 1;
    return 0; // choice == 4 (모르겠음)
}

// 성격 유형 검사
char* solution(const char* survey[], size_t survey_len, int choices[], size_t choices_len) {
    // 성격 유형 점수 저장용 (26 알파벳 배열 활용)
    int score[26] = {0};

    for (size_t i = 0; i < survey_len; i++) {
        char disagree = survey[i][0]; // 비동의 유형
        char agree = survey[i][1];    // 동의 유형
        int choice = choices[i];
        int s = get_score(choice);

        if (choice < 4) {
            score[disagree - 'A'] += s; // 비동의 쪽 점수
        } else if (choice > 4) {
            score[agree - 'A'] += s;    // 동의 쪽 점수
        }
    }

    // 결과 문자열 (R/T, C/F, J/M, A/N)
    char* answer = (char*)malloc(5); // 4글자 + NULL
    answer[4] = '\0';

    answer[0] = (score['R' - 'A'] >= score['T' - 'A']) ? 'R' : 'T';
    answer[1] = (score['C' - 'A'] >= score['F' - 'A']) ? 'C' : 'F';
    answer[2] = (score['J' - 'A'] >= score['M' - 'A']) ? 'J' : 'M';
    answer[3] = (score['A' - 'A'] >= score['N' - 'A']) ? 'A' : 'N';

    return answer;
}
