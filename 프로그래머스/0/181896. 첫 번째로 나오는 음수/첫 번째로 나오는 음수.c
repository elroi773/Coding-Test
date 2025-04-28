#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// num_list_len은 배열 num_list의 길이입니다.
int solution(int num_list[], size_t num_list_len) {
    for (int i = 0; i < num_list_len; i++) {  // 배열을 처음부터 끝까지 순회
        if (num_list[i] < 0) {                // 음수를 만나면
            return i;                         // 그 인덱스를 바로 반환
        }
    }
    return -1;  // 끝까지 음수를 못 찾으면 -1 반환
}
