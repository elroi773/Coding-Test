function solution(s) {
    let answer = 0;
    let x = ''; // 기준 문자
    let xCount = 0;
    let otherCount = 0;

    for (let i = 0; i < s.length; i++) {
        if (xCount === 0) {
            x = s[i];
            xCount = 1;
            otherCount = 0;
        } else {
            if (s[i] === x) {
                xCount++;
            } else {
                otherCount++;
            }

            if (xCount === otherCount) {
                answer++;
                xCount = 0;
                otherCount = 0;
            }
        }
    }

    // 마지막 덩어리가 남아 있을 수 있음 (횟수가 같아지지 않은 경우)
    if (xCount !== 0 || otherCount !== 0) {
        answer++;
    }

    return answer;
}
