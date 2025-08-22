function solution(ingredient) {
    let answer = 0;
    let stack = [];

    for (let i of ingredient) {
        stack.push(i);

        // 스택 끝 4개 확인
        if (stack.length >= 4) {
            let len = stack.length;
            if (stack[len-4] === 1 && stack[len-3] === 2 &&
                stack[len-2] === 3 && stack[len-1] === 1) {
                // 햄버거 완성 → pop 4개
                stack.splice(len-4, 4);
                answer++;
            }
        }
    }

    return answer;
}
