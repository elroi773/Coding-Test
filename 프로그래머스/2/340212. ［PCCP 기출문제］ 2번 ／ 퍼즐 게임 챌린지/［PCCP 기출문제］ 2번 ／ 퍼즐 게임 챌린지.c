#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

long long calc_time(int diffs[], int times[], int n, int level, long long limit) {
    long long total = 0;

    // 첫 퍼즐은 무조건 times[0]
    total += times[0];
    if (total > limit) return total;

    for (int i = 1; i < n; i++) {
        if (level >= diffs[i]) {
            total += times[i];
        } else {
            long long fail = (long long)(diffs[i] - level);
            total += fail * (times[i] + times[i - 1]) + times[i];
        }

        if (total > limit) return total;
    }

    return total;
}

// diffs_len은 배열 diffs의 길이입니다.
// times_len은 배열 times의 길이입니다.
int solution(int diffs[], size_t diffs_len, int times[], size_t times_len, long long limit) {
    int n = diffs_len;

    // 최대 난이도 찾기
    int max_diff = 0;
    for (int i = 0; i < n; i++) {
        if (diffs[i] > max_diff) max_diff = diffs[i];
    }

    int left = 1, right = max_diff;
    int answer = max_diff;

    while (left <= right) {
        int mid = (left + right) / 2;

        long long t = calc_time(diffs, times, n, mid, limit);

        if (t <= limit) {
            answer = mid;
            right = mid - 1;
        } else {
            left = mid + 1;
        }
    }

    return answer;
}
