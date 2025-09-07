function solution(x) {
    // 자릿수 합 구하기
    const sum = String(x)
        .split("")
        .map(Number)
        .reduce((a, b) => a + b, 0);

    // 하샤드 수 판별
    return x % sum === 0;
}
