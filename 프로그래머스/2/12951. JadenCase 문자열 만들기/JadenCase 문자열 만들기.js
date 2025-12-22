function solution(s) {
    let answer = '';
    let isStart = true; // 단어 시작 여부

    for (let i = 0; i < s.length; i++) {
        const c = s[i];

        if (c === ' ') {
            answer += c;
            isStart = true;
        } else {
            if (isStart) {
                answer += c.toUpperCase();
                isStart = false;
            } else {
                answer += c.toLowerCase();
            }
        }
    }
    return answer;
}
