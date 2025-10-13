#include <stdio.h>
#include <stdlib.h>

// 크기별 개수를 저장할 구조체
typedef struct {
    int size;   // 귤 크기
    int count;  // 해당 크기의 개수
} TangerineCount;

// qsort용 비교 함수 (내림차순)
int compare(const void* a, const void* b) {
    TangerineCount* t1 = (TangerineCount*)a;
    TangerineCount* t2 = (TangerineCount*)b;
    return t2->count - t1->count; // count 기준 내림차순
}

int solution(int k, int tangerine[], size_t tangerine_len) {
    TangerineCount* counts = malloc(sizeof(TangerineCount) * tangerine_len);
    int unique = 0; // 서로 다른 크기 수

    // 귤 크기별 개수 세기
    for (size_t i = 0; i < tangerine_len; i++) {
        int found = 0;
        for (int j = 0; j < unique; j++) {
            if (counts[j].size == tangerine[i]) {
                counts[j].count++;
                found = 1;
                break;
            }
        }
        if (!found) {
            counts[unique].size = tangerine[i];
            counts[unique].count = 1;
            unique++;
        }
    }

    // 개수 기준으로 내림차순 정렬
    qsort(counts, unique, sizeof(TangerineCount), compare);

    // 많은 크기부터 담기
    int total = 0;
    int answer = 0;
    for (int i = 0; i < unique; i++) {
        total += counts[i].count;
        answer++;
        if (total >= k) break;
    }

    free(counts);
    return answer;
}