#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int solution(const char* babbling[], size_t babbling_len) {
    char* words[4] = {"aya", "ye", "woo", "ma"};
    int answer = 0;

    for (size_t i = 0; i < babbling_len; i++) {
        const char* s = babbling[i];
        int len = strlen(s);
        int pos = 0;
        int last = -1; // 직전에 쓴 발음 인덱스 (중복 방지)
        bool valid = true;

        while (pos < len) {
            bool matched = false;

            for (int j = 0; j < 4; j++) {
                int wlen = strlen(words[j]);
                if (strncmp(s + pos, words[j], wlen) == 0 && j != last) {
                    pos += wlen;
                    last = j;
                    matched = true;
                    break;
                }
            }

            if (!matched) {
                valid = false;
                break;
            }
        }

        if (valid) answer++;
    }

    return answer;
}
