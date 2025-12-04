function solution(w, h) {
    // 최대공약수 구하기 (유클리드 호제법)
    const gcd = (a, b) => {
        while (b !== 0) {
            const r = a % b;
            a = b;
            b = r;
        }
        return a;
    };

    const g = gcd(w, h);
    // 전체 정사각형 개수 - (대각선이 지나가서 못 쓰는 정사각형 개수)
    // 못 쓰는 정사각형 개수 = w + h - gcd(w, h)
    return w * h - (w + h - g);
}