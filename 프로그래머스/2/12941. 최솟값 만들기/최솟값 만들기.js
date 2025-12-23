function solution(A, B) {
    let answer = 0;

    // A 오름차순 정렬
    A.sort((a, b) => a - b);

    // B 내림차순 정렬
    B.sort((a, b) => b - a);

    // 같은 인덱스끼리 곱해서 누적
    for (let i = 0; i < A.length; i++) {
        answer += A[i] * B[i];
    }

    return answer;
}
