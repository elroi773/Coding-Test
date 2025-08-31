function solution(n) {
    // n+1 크기의 배열을 true로 초기화 (0~n까지 포함)
    const isPrime = new Array(n + 1).fill(true);
    isPrime[0] = false; // 0은 소수 아님
    isPrime[1] = false; // 1은 소수 아님

    // 2부터 sqrt(n)까지 검사
    for (let i = 2; i * i <= n; i++) {
        if (isPrime[i]) {
            // i의 배수들은 소수가 아님
            for (let j = i * i; j <= n; j += i) {
                isPrime[j] = false;
            }
        }
    }

    // true인 값(소수)만 세기
    return isPrime.filter(v => v).length;
}
