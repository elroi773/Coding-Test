function solution(n, k) {
    // n을 k진수 문자열로 변환
    const toBaseK = (num, base) => {
        let s = '';
        while (num > 0) {
            s = (num % base) + s;
            num = Math.floor(num / base);
        }
        return s || '0';
    };

    const isPrime = (x) => {
        if (x < 2) return false;
        if (x === 2) return true;
        if (x % 2 === 0) return false;
        const limit = Math.floor(Math.sqrt(x));
        for (let i = 3; i <= limit; i += 2) {
            if (x % i === 0) return false;
        }
        return true;
    };

    const baseStr = toBaseK(n, k);      // 예: 437674, 3 → "211020101011"
    const parts = baseStr.split(/0+/);  // 0 기준으로 끊기 (연속된 0도 한 번에)

    let answer = 0;

    for (const p of parts) {
        if (p === '') continue;        // 빈 문자열은 무시
        const num = Number(p);         // '211' → 211 (10진수로 해석)
        if (isPrime(num)) answer++;
    }

    return answer;
}