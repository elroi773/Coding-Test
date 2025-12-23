function solution(n) {
    const answer = [];

    function hanoi(k, start, via, end) {
        if (k === 1) {
            answer.push([start, end]);
            return;
        }

        // 1. n-1개를 보조 기둥으로
        hanoi(k - 1, start, end, via);

        // 2. 가장 큰 원판 이동
        answer.push([start, end]);

        // 3. n-1개를 목표 기둥으로
        hanoi(k - 1, via, start, end);
    }

    hanoi(n, 1, 2, 3);
    return answer;
}
