#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// date1_len은 배열 date1의 길이입니다.
// date2_len은 배열 date2의 길이입니다.
int solution(int date1[], size_t date1_len, int date2[], size_t date2_len) {
    // 연도 비교
    if (date1[0] < date2[0]) return 1;
    else if (date1[0] > date2[0]) return 0;
    
    // 연도가 같으면 월 비교
    if (date1[1] < date2[1]) return 1;
    else if (date1[1] > date2[1]) return 0;
    
    // 연도와 월이 같으면 일 비교
    if (date1[2] < date2[2]) return 1;
    else return 0;
}
