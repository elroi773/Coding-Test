function solution(n) {
    for (let x = 1; x < n; x++) {
        if (n % x === 1) {
            return x;
        }
    }
    return -1; // 안전장치, 실제로는 여기까지 오지 않음
}

// 테스트
console.log(solution(10)); // 3
console.log(solution(12)); // 11
