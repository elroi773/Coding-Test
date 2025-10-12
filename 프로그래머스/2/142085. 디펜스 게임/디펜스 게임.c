#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// ---- 최대 힙 구현 ----
void swap(int *a, int *b) {
    int tmp = *a;
    *a = *b;
    *b = tmp;
}

void heap_push(int heap[], int *size, int val) {
    heap[++(*size)] = val; // heap[0]은 비워두고 인덱스 1부터 사용
    int idx = *size;
    while (idx > 1 && heap[idx / 2] < heap[idx]) { // 부모보다 크면 swap
        swap(&heap[idx / 2], &heap[idx]);
        idx /= 2;
    }
}

int heap_pop(int heap[], int *size) {
    if (*size == 0) return 0;
    int top = heap[1];
    heap[1] = heap[(*size)--];
    int idx = 1;
    while (1) {
        int left = idx * 2;
        int right = left + 1;
        int largest = idx;

        if (left <= *size && heap[left] > heap[largest]) largest = left;
        if (right <= *size && heap[right] > heap[largest]) largest = right;

        if (largest == idx) break;
        swap(&heap[idx], &heap[largest]);
        idx = largest;
    }
    return top;
}

// ---- 메인 로직 ----
int solution(int n, int k, int enemy[], size_t enemy_len) {
    int heap[enemy_len + 1]; // 최대 enemy 개수만큼
    int heap_size = 0;

    for (int i = 0; i < enemy_len; i++) {
        n -= enemy[i];
        heap_push(heap, &heap_size, enemy[i]);

        // 병사가 부족하면
        if (n < 0) {
            if (k > 0) {
                int largest = heap_pop(heap, &heap_size); // 가장 큰 적 수 제거
                n += largest; // 병사 수 복원
                k--;
            } else {
                return i; // 막은 라운드 수
            }
        }
    }
    return enemy_len; // 모든 라운드 막음
}
