function solution(a, b, n) {
    let answer = 0;

    while (n >= a) {
        const exchanged = Math.floor(n / a) * b; // 교환해서 받은 콜라
        answer += exchanged;
        n = Math.floor(n / a) * b + (n % a); // 받은 콜라를 마신 후 생긴 병 + 남은 병
    }

    return answer;
}
