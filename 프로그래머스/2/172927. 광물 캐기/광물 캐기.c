#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int minerals[5]; // 광물 (최대 5개)
    int size;        // 실제 들어간 개수
    int hardness;    // 돌 곡괭이로 캤을 때 피로도
} Group;

// 피로도 표: [곡괭이][광물]
int fatigue[3][3] = {
    {1, 1, 1},   // 다이아 곡괭이
    {5, 1, 1},   // 철 곡괭이
    {25, 5, 1}   // 돌 곡괭이
};

// 문자열을 숫자로 변환
int mineralToInt(const char* mineral) {
    if (strcmp(mineral, "diamond") == 0) return 0;
    if (strcmp(mineral, "iron") == 0) return 1;
    return 2; // stone
}

// 그룹의 hardness(돌 곡괭이 피로도) 계산
int calcHardness(Group* g) {
    int sum = 0;
    for (int i = 0; i < g->size; i++) {
        sum += fatigue[2][g->minerals[i]]; // 돌 곡괭이 기준
    }
    return sum;
}

// hardness 기준 내림차순 정렬
int cmp(const void* a, const void* b) {
    Group* g1 = (Group*)a;
    Group* g2 = (Group*)b;
    return g2->hardness - g1->hardness; 
}

int solution(int picks[], size_t picks_len, const char* minerals[], size_t minerals_len) {
    int total_picks = picks[0] + picks[1] + picks[2];
    int group_count = (minerals_len + 4) / 5;
    if (group_count > total_picks) group_count = total_picks;

    // 1) 그룹 생성
    Group* groups = (Group*)malloc(sizeof(Group) * group_count);
    for (int i = 0; i < group_count; i++) {
        groups[i].size = 0;
        for (int j = 0; j < 5 && i*5 + j < minerals_len; j++) {
            groups[i].minerals[groups[i].size++] = mineralToInt(minerals[i*5 + j]);
        }
        groups[i].hardness = calcHardness(&groups[i]);
    }

    // 2) hardness 기준 정렬
    qsort(groups, group_count, sizeof(Group), cmp);

    // 3) 곡괭이 순서대로 배열 만들기 (좋은 곡괭이부터)
    int picks_order[15]; // 최대 5+5+5=15
    int idx = 0;
    for (int i = 0; i < 3; i++) {
        for (int j = 0; j < picks[i]; j++) {
            picks_order[idx++] = i;
        }
    }

    // 4) 실제 캐기
    int answer = 0;
    for (int i = 0; i < group_count; i++) {
        int pick = picks_order[i]; // 현재 곡괭이
        for (int j = 0; j < groups[i].size; j++) {
            int m = groups[i].minerals[j];
            answer += fatigue[pick][m];
        }
    }

    free(groups);
    return answer;
}
