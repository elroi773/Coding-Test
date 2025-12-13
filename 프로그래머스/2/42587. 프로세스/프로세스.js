function solution(priorities, location) {
    let queue = priorities.map((p, idx) => [p, idx]);
    let order = 0;

    while (queue.length > 0) {
        const current = queue.shift();

        // 큐에 더 높은 우선순위가 있는지 확인
        if (queue.some(item => item[0] > current[0])) {
            queue.push(current);
        } else {
            // 실행
            order++;
            if (current[1] === location) {
                return order;
            }
        }
    }
}
