#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    char name[15];
    int start;
    int playtime;
} Task;

int toMinutes(const char* time) {
    int h, m;
    sscanf(time, "%d:%d", &h, &m);
    return h * 60 + m;
}

int compare(const void* a, const void* b) {
    Task* t1 = (Task*)a;
    Task* t2 = (Task*)b;
    return t1->start - t2->start;
}

char** solution(const char*** plans, size_t plans_rows, size_t plans_cols) {
    // Task 배열로 변환
    Task* tasks = (Task*)malloc(sizeof(Task) * plans_rows);
    for (int i = 0; i < plans_rows; i++) {
        strcpy(tasks[i].name, plans[i][0]);
        tasks[i].start = toMinutes(plans[i][1]);
        tasks[i].playtime = atoi(plans[i][2]);
    }

    // 시작 시간 순으로 정렬
    qsort(tasks, plans_rows, sizeof(Task), compare);

    // 결과 배열
    char** answer = (char**)malloc(sizeof(char*) * plans_rows);
    int answerSize = 0;

    // 스택: [이름, 남은 시간]
    Task* stack = (Task*)malloc(sizeof(Task) * plans_rows);
    int top = -1;

    for (int i = 0; i < plans_rows; i++) {
        int now = tasks[i].start;
        int playtime = tasks[i].playtime;

        // 다음 과제 시작 시각 (없으면 무한대)
        int nextStart = (i < plans_rows - 1) ? tasks[i + 1].start : 1e9;

        if (now + playtime <= nextStart) {
            // 현재 과제를 끝낼 수 있음
            answer[answerSize] = (char*)malloc(15);
            strcpy(answer[answerSize++], tasks[i].name);

            int freeTime = nextStart - (now + playtime);

            // 멈춘 과제 처리
            while (freeTime > 0 && top >= 0) {
                Task prev = stack[top--];
                if (prev.playtime <= freeTime) {
                    freeTime -= prev.playtime;
                    answer[answerSize] = (char*)malloc(15);
                    strcpy(answer[answerSize++], prev.name);
                } else {
                    prev.playtime -= freeTime;
                    stack[++top] = prev;
                    freeTime = 0;
                }
            }
        } else {
            // 다 못 끝내고 멈춤
            Task remain;
            strcpy(remain.name, tasks[i].name);
            remain.playtime = playtime - (nextStart - now);
            stack[++top] = remain;
        }
    }

    // 스택에 남은 과제 처리
    while (top >= 0) {
        Task prev = stack[top--];
        answer[answerSize] = (char*)malloc(15);
        strcpy(answer[answerSize++], prev.name);
    }

    free(tasks);
    free(stack);

    return answer;
}
