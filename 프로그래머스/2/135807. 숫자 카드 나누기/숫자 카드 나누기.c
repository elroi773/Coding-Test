#include <stdio.h>
#include <stdbool.h>

// 최대공약수 (GCD) 함수
int gcd(int a, int b) {
    while (b != 0) {
        int tmp = a % b;
        a = b;
        b = tmp;
    }
    return a;
}

// 배열의 전체 GCD 계산
int get_gcd_array(int arr[], size_t len) {
    int result = arr[0];
    for (size_t i = 1; i < len; i++) {
        result = gcd(result, arr[i]);
    }
    return result;
}

// 배열의 모든 원소가 x로 나누어지지 않는지 검사
bool no_divisible(int arr[], size_t len, int x) {
    for (size_t i = 0; i < len; i++) {
        if (arr[i] % x == 0) return false;
    }
    return true;
}

int solution(int arrayA[], size_t arrayA_len, int arrayB[], size_t arrayB_len) {
    int gcdA = get_gcd_array(arrayA, arrayA_len);
    int gcdB = get_gcd_array(arrayB, arrayB_len);
    
    int candidateA = 0, candidateB = 0;
    
    if (no_divisible(arrayB, arrayB_len, gcdA))
        candidateA = gcdA;
    
    if (no_divisible(arrayA, arrayA_len, gcdB))
        candidateB = gcdB;
    
    int answer = candidateA > candidateB ? candidateA : candidateB;
    return answer;
}
