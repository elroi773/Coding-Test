#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>

// number_len은 배열 number의 길이입니다.
int solution(int number[], size_t number_len) {
    int answer = 0;
    
    // 세 학생을 선택하는 모든 조합 확인
    for (int i = 0; i < number_len - 2; i++) {
        for (int j = i + 1; j < number_len - 1; j++) {
            for (int k = j + 1; k < number_len; k++) {
                if (number[i] + number[j] + number[k] == 0) {
                    answer++;
                }
            }
        }
    }
    
    return answer;
}

int main() {
    int arr1[] = {-2, 3, 0, 2, -5};
    int arr2[] = {-3, -2, -1, 0, 1, 2, 3};
    int arr3[] = {-1, 1, -1, 1};

    printf("%d\n", solution(arr1, 5));  // 2
    printf("%d\n", solution(arr2, 7));  // 5
    printf("%d\n", solution(arr3, 4));  // 0

    return 0;
}
