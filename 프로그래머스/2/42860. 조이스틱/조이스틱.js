function solution(name) {
    let answer = 0;
    const n = name.length;

    // 1. 상/하 이동 (문자 변경)
    for (let i = 0; i < n; i++) {
        const ch = name[i];
        // 위로 이동 vs 아래로 이동
        answer += Math.min(ch.charCodeAt() - 65, 91 - ch.charCodeAt());
    }

    // 2. 좌/우 이동 최소화
    let move = n - 1; // 기본적으로 오른쪽으로 쭉 가는 경우

    for (let i = 0; i < n; i++) {
        let next = i + 1;

        // 연속된 A 구간 찾기
        while (next < n && name[next] === 'A') {
            next++;
        }

        // 2가지 전략 비교 (A 구간을 우회하는 최적 경로)
        // 1) i까지 갔다가 뒤로 돌아가기
        move = Math.min(move, i * 2 + (n - next));
        // 2) 끝에서 시작해서 i로 돌아오기
        move = Math.min(move, i + (n - next) * 2);
    }

    return answer + move;
}
