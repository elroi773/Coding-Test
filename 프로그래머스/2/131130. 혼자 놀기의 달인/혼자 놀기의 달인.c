#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// cards_len은 배열 cards의 길이입니다.
int solution(int cards[], size_t cards_len) {
    // 방문 여부 체크 배열
    bool *visited = (bool *)calloc(cards_len, sizeof(bool));
    if (!visited) return 0;

    int *group_sizes = (int *)malloc(sizeof(int) * cards_len);
    if (!group_sizes) {
        free(visited);
        return 0;
    }
    int group_count = 0;

    // 각 시작 상자에 대해 사이클(그룹) 크기 구하기
    for (size_t i = 0; i < cards_len; i++) {
        if (visited[i]) continue;   // 이미 어떤 그룹에 들어간 상자면 패스

        int count = 0;
        int cur = (int)i;

        // 현재 상자에서 시작해, 이미 연 상자를 다시 만날 때까지 진행
        while (!visited[cur]) {
            visited[cur] = true;
            count++;

            // cards[cur]은 "다음에 열어야 하는 상자의 번호" (1-based)
            // 배열 인덱스로 쓰기 위해 -1
            cur = cards[cur] - 1;
        }

        group_sizes[group_count++] = count;
    }

    // 그룹이 2개 미만이면 점수는 0
    if (group_count < 2) {
        free(visited);
        free(group_sizes);
        return 0;
    }

    // 그룹 크기를 내림차순 정렬 (간단하게 버블/선택 정렬 사용해도 충분)
    for (int i = 0; i < group_count - 1; i++) {
        for (int j = i + 1; j < group_count; j++) {
            if (group_sizes[i] < group_sizes[j]) {
                int tmp = group_sizes[i];
                group_sizes[i] = group_sizes[j];
                group_sizes[j] = tmp;
            }
        }
    }

    int answer = group_sizes[0] * group_sizes[1];

    free(visited);
    free(group_sizes);

    return answer;
}
