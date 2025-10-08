function solution(numbers) {
    const n = numbers.length;
    const answer = new Array(n).fill(-1); // 초기값 -1
    const stack = []; // 인덱스를 저장할 스택

    for (let i = 0; i < n; i++) {
        // 현재 값이 스택 최상단 인덱스의 값보다 크면 뒷 큰수로 채움
        while (stack.length > 0 && numbers[i] > numbers[stack[stack.length - 1]]) {
            const idx = stack.pop();
            answer[idx] = numbers[i];
        }
        stack.push(i); // 현재 인덱스를 스택에 추가
    }

    return answer;
}