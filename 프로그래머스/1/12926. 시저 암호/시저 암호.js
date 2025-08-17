function solution(s, n) {
    let answer = '';

    for (let i = 0; i < s.length; i++) {
        let ch = s[i];
        if (ch === ' ') {
            answer += ' '; // 공백은 그대로
        } else {
            let code = s.charCodeAt(i);

            if (code >= 65 && code <= 90) {
                // 대문자 A-Z
                answer += String.fromCharCode((code - 65 + n) % 26 + 65);
            } else if (code >= 97 && code <= 122) {
                // 소문자 a-z
                answer += String.fromCharCode((code - 97 + n) % 26 + 97);
            }
        }
    }

    return answer;
}
