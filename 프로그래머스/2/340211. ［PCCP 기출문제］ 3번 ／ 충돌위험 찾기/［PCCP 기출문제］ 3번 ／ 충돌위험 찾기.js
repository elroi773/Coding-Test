function solution(points, routes) {
    const x = routes.length;       // 로봇 수
    const m = routes[0].length;    // 각 로봇의 경로 길이

    // 로봇 상태
    const rs = new Array(x);       // 현재 r
    const cs = new Array(x);       // 현재 c
    const segIdx = new Array(x);   // 현재 "도착 포인트" 인덱스 (1 ~ m-1)
    const active = new Array(x);   // 센터 안에 있는지 여부

    // 초기 상태 설정
    for (let i = 0; i < x; i++) {
        const startPointId = routes[i][0] - 1; // 0-based index
        rs[i] = points[startPointId][0];
        cs[i] = points[startPointId][1];
        segIdx[i] = 1;       // routes[i][0] -> routes[i][1] 구간부터 시작
        active[i] = true;
    }

    let answer = 0;

    while (true) {
        let anyActive = false;
        let collisionsThisTime = 0;

        // 이번 시각(t)의 좌표별 로봇 개수 세기
        const count = new Map(); // key: "r,c", value: 로봇 수

        for (let i = 0; i < x; i++) {
            if (!active[i]) continue;

            anyActive = true;
            const r = rs[i];
            const c = cs[i];
            const key = r + ',' + c;

            const prev = count.get(key) || 0;
            const cur = prev + 1;
            count.set(key, cur);

            // 1 -> 2로 바뀌는 순간만 위험 상황 1회 추가
            if (cur === 2) {
                collisionsThisTime++;
            }
        }

        // 더 이상 센터 안에 로봇이 없으면 종료
        if (!anyActive) break;

        // 이번 시각의 위험 상황 합산
        answer += collisionsThisTime;

        // 다음 시각(t+1)을 위해 로봇 이동
        for (let i = 0; i < x; i++) {
            if (!active[i]) continue;

            let idx = segIdx[i]; // 현재 도착 포인트 인덱스

            if (idx >= m) {
                // 이론상 거의 안 오지만 안전장치
                active[i] = false;
                continue;
            }

            let targetId = routes[i][idx] - 1;
            let tr = points[targetId][0];
            let tc = points[targetId][1];

            // 이미 목표 포인트 위에 있는 경우
            if (rs[i] === tr && cs[i] === tc) {
                if (idx === m - 1) {
                    // 마지막 포인트에 도착 → 다음 시각부터 센터 밖
                    active[i] = false;
                    continue;
                } else {
                    // 다음 구간으로 넘어가기 (route[idx] -> route[idx+1])
                    idx++;
                    segIdx[i] = idx;
                    targetId = routes[i][idx] - 1;
                    tr = points[targetId][0];
                    tc = points[targetId][1];
                }
            }

            if (!active[i]) continue; // 바로 위에서 나간 경우 방지

            // r 우선, 그 다음 c 방향으로 한 칸 이동
            if (rs[i] !== tr) {
                rs[i] += (tr > rs[i]) ? 1 : -1;
            } else if (cs[i] !== tc) {
                cs[i] += (tc > cs[i]) ? 1 : -1;
            }
        }
    }

    return answer;
}