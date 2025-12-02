function solution(s) {
    const n = s.length;
    let answer = 0;

    // 올바른 괄호 문자열 판단 함수
    function isValid(str) {
        const stack = [];
        const pair = {
            ')': '(',
            ']': '[',
            '}': '{'
        };

        for (const c of str) {
            if (c === '(' || c === '[' || c === '{') {
                stack.push(c);
            } else {
                if (stack.length === 0 || stack[stack.length - 1] !== pair[c]) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.length === 0;
    }

    // 문자열 회전하며 검사
    for (let x = 0; x < n; x++) {
        const rotated = s.slice(x) + s.slice(0, x);
        if (isValid(rotated)) {
            answer++;
        }
    }

    return answer;
}
