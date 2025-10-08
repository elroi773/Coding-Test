#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <string.h>

// HH:MM -> 분(minute)으로 변환
int to_minutes(const char *time) {
    int h, m;
    sscanf(time, "%d:%d", &h, &m);
    return h * 60 + m;
}

// 예약 시작 시간 기준 정렬용 비교 함수
int compare(const void *a, const void *b) {
    int *x = *(int **)a;
    int *y = *(int **)b;
    return x[0] - y[0];
}

int solution(const char ***book_time, size_t book_time_rows, size_t book_time_cols) {
    // 1. 예약 정보를 분 단위로 변환
    int **times = (int **)malloc(sizeof(int *) * book_time_rows);
    for (size_t i = 0; i < book_time_rows; i++) {
        times[i] = (int *)malloc(sizeof(int) * 2);
        times[i][0] = to_minutes(book_time[i][0]);           // 시작 시각
        times[i][1] = to_minutes(book_time[i][1]) + 10;      // 종료 시각 + 청소 시간
    }

    // 2. 시작 시각 기준 정렬
    qsort(times, book_time_rows, sizeof(int *), compare);

    // 3. 방들의 종료 시각(청소 완료 시간) 관리
    int *rooms = (int *)malloc(sizeof(int) * book_time_rows);
    int room_count = 0;

    for (size_t i = 0; i < book_time_rows; i++) {
        int start = times[i][0];
        int end = times[i][1];

        bool reused = false;

        // 현재 방 중에서 청소 완료된 방이 있는지 확인
        for (int j = 0; j < room_count; j++) {
            if (rooms[j] <= start) {  // 청소 끝났다면 재사용
                rooms[j] = end;
                reused = true;
                break;
            }
        }

        if (!reused) {  // 재사용 불가 → 새 방 추가
            rooms[room_count++] = end;
        }
    }

    // 4. 결과: 방 개수
    int answer = room_count;

    // 메모리 해제
    for (size_t i = 0; i < book_time_rows; i++) {
        free(times[i]);
    }
    free(times);
    free(rooms);

    return answer;
}
