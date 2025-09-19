function solution(park, routes) {
    const H = park.length;
    const W = park[0].length;

    // 시작 위치 찾기
    let r = 0, c = 0;
    for (let i = 0; i < H; i++) {
        for (let j = 0; j < W; j++) {
            if (park[i][j] === 'S') {
                r = i; c = j;
            }
        }
    }

    // 방향 정의
    const dir = {
        N: [-1, 0],
        S: [1, 0],
        W: [0, -1],
        E: [0, 1]
    };

    // 명령 실행
    for (let route of routes) {
        let [op, distStr] = route.split(" ");
        let dist = parseInt(distStr);

        let nr = r, nc = c;
        let valid = true;

        for (let step = 1; step <= dist; step++) {
            let tr = r + dir[op][0] * step;
            let tc = c + dir[op][1] * step;

            // 범위 벗어나면 무효
            if (tr < 0 || tr >= H || tc < 0 || tc >= W) {
                valid = false;
                break;
            }
            // 장애물 만나면 무효
            if (park[tr][tc] === 'X') {
                valid = false;
                break;
            }
            nr = tr; nc = tc;
        }

        if (valid) { // 이동 가능하면 위치 업데이트
            r = nr;
            c = nc;
        }
    }

    return [r, c];
}
