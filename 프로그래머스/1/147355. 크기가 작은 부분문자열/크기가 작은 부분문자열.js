function solution(t, p) {
    let answer = 0;
    let len = p.length;
    let pNum = BigInt(p);  // p가 최대 18자리 수이므로 BigInt 사용

    for (let i = 0; i <= t.length - len; i++) {
        let sub = t.slice(i, i + len);
        let subNum = BigInt(sub);
        if (subNum <= pNum) {
            answer++;
        }
    }

    return answer;
}
