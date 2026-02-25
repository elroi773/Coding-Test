#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

static int cmp_int(const void *a, const void *b) {
    int x = *(const int *)a;
    int y = *(const int *)b;
    if (x < y) return -1;
    if (x > y) return 1;
    return 0;
}

// A_len은 배열 A의 길이입니다.
// B_len은 배열 B의 길이입니다.
int solution(int A[], size_t A_len, int B[], size_t B_len) {
    // 문제 조건상 길이는 같음
    size_t n = A_len;
    
    // 원본 훼손 방지용 복사본
    int *a = (int *)malloc(sizeof(int) * n);
    int *b = (int *)malloc(sizeof(int) * n);
    if (a == NULL || b == NULL) {
        free(a);
        free(b);
        return 0;
    }
    
    for (size_t i = 0; i < n; i++) {
        a[i] = A[i];
        b[i] = B[i];
    }
    
    qsort(a, n, sizeof(int), cmp_int);
    qsort(b, n, sizeof(int), cmp_int);
    
    size_t i = 0; // A 포인터
    size_t j = 0; // B 포인터
    int answer = 0;
    
    // B에서 현재 A를 이길 수 있는 가장 작은 수를 매칭
    while (i < n && j < n) {
        if (b[j] > a[i]) {
            answer++;
            i++;
            j++;
        } else {
            // 현재 b[j]는 a[i]를 못 이기므로 버림
            j++;
        }
    }
    
    free(a);
    free(b);
    return answer;
}