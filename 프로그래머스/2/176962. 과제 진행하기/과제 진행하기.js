function solution(plans) {
    // hh:mm -> 분 단위 변환 함수
    function toMinutes(time) {
        let [h, m] = time.split(":").map(Number);
        return h * 60 + m;
    }

    // plans를 숫자형으로 변환
    let tasks = plans.map(([name, start, playtime]) => {
        return {
            name,
            start: toMinutes(start),
            playtime: Number(playtime)
        };
    });

    // 시작 시간 기준 정렬
    tasks.sort((a, b) => a.start - b.start);

    let answer = [];
    let stack = [];

    for (let i = 0; i < tasks.length; i++) {
        let cur = tasks[i];
        let now = cur.start;
        let playtime = cur.playtime;

        // 다음 과제 시작 시각 (없으면 매우 큰 값)
        let nextStart = (i < tasks.length - 1) ? tasks[i + 1].start : Infinity;

        if (now + playtime <= nextStart) {
            // 현재 과제 끝낼 수 있음
            answer.push(cur.name);

            let freeTime = nextStart - (now + playtime);

            // 스택에서 멈춘 과제 꺼내기
            while (freeTime > 0 && stack.length > 0) {
                let prev = stack.pop();
                if (prev.playtime <= freeTime) {
                    freeTime -= prev.playtime;
                    answer.push(prev.name);
                } else {
                    prev.playtime -= freeTime;
                    stack.push(prev);
                    break;
                }
            }
        } else {
            // 다 못 끝내고 멈춰야 함
            cur.playtime = playtime - (nextStart - now);
            stack.push(cur);
        }
    }

    // 스택에 남은 과제 처리
    while (stack.length > 0) {
        answer.push(stack.pop().name);
    }

    return answer;
}
