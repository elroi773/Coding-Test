#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

int solution(const char* skill, const char* skill_trees[], size_t skill_trees_len) {
    int answer = 0;

    for (size_t i = 0; i < skill_trees_len; i++) {
        const char* tree = skill_trees[i];
        int idx = 0;          // skill 문자열에서 현재 배워야 할 스킬 위치
        bool valid = true;

        for (size_t j = 0; j < strlen(tree); j++) {
            char c = tree[j];
            char* pos = strchr(skill, c); // skill 안에 포함된 스킬인지 확인

            if (pos != NULL) {
                int skill_pos = pos - skill;

                // 선행 순서에 맞지 않으면 불가능한 스킬트리
                if (skill_pos == idx) {
                    idx++;
                } else {
                    valid = false;
                    break;
                }
            }
        }

        if (valid) answer++;
    }

    return answer;
}
