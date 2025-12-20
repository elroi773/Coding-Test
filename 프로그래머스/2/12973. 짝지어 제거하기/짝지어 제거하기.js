function solution(s) {
    const stack = [];

    for (let i = 0; i < s.length; i++) {
        const c = s[i];

        if (stack.length > 0 && stack[stack.length - 1] === c) {
            stack.pop();      // 짝이면 제거
        } else {
            stack.push(c);    // 아니면 저장
        }
    }

    return stack.length === 0 ? 1 : 0;
}
