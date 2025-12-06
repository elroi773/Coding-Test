function solution(s) {
    let transformCnt = 0;
    let removedZeroCnt = 0;

    while (s !== "1") {
        const len = s.length;
        let ones = 0;

        for (let i = 0; i < len; i++) {
            if (s[i] === '1') ones++;
        }

        removedZeroCnt += (len - ones);
        transformCnt++;

        // 다음 문자열 = 1의 개수의 2진수 표현
        s = ones.toString(2);
    }

    return [transformCnt, removedZeroCnt];
}