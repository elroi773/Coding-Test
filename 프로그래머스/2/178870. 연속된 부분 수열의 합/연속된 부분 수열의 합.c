#include <stdio.h>
#include <stdlib.h>

int* solution(int sequence[], size_t sequence_len, int k) {
    int start = 0, end = 0;
    int current_sum = sequence[0];

    int min_len = (int)sequence_len;  // 최소 길이
    int *answer = (int*)malloc(2 * sizeof(int)); // 결과는 시작, 끝 인덱스
    answer[0] = 0;
    answer[1] = (int)sequence_len - 1;

    while (start < sequence_len && end < sequence_len) {
        if (current_sum < k) {
            end++;
            if (end < sequence_len)
                current_sum += sequence[end];
        } else if (current_sum > k) {
            current_sum -= sequence[start];
            start++;
        } else { // current_sum == k
            if ((end - start) < min_len) {
                min_len = end - start;
                answer[0] = start;
                answer[1] = end;
            }
            current_sum -= sequence[start];
            start++;
        }
    }

    return answer;
}