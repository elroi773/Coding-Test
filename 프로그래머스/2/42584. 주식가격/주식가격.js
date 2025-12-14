function solution(prices) {
    const n = prices.length;
    const answer = new Array(n).fill(0);
    const stack = []; // 인덱스 저장

    for (let i = 0; i < n; i++) {
        // 가격이 떨어지는 순간
        while (stack.length && prices[i] < prices[stack[stack.length - 1]]) {
            const idx = stack.pop();
            answer[idx] = i - idx;
        }
        stack.push(i);
    }

    // 끝까지 가격이 안 떨어진 경우
    while (stack.length) {
        const idx = stack.pop();
        answer[idx] = n - 1 - idx;
    }

    return answer;
}
