function solution(s) {
    let sign = 1;
    let start = 0;
    let answer = 0;

    if (s[0] === '-') {
        sign = -1;
        start = 1;
    } else if (s[0] === '+') {
        start = 1;
    }

    for (let i = start; i < s.length; i++) {
        answer = answer * 10 + (s[i] - '0');
    }

    return sign * answer;
}
