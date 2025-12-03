function solution(r1, r2) {
    let answer = 0;

    const R1 = r1;
    const R2 = r2;
    const R1sq = R1 * R1;
    const R2sq = R2 * R2;

    for (let x = 0; x <= r2; x++) {
        const xx = x * x;

        // 바깥 원에서의 최대 y
        const maxY = Math.floor(Math.sqrt(R2sq - xx));

        // 안쪽 원에서의 최소 y
        const diff1 = R1sq - xx;
        const minY = diff1 > 0 ? Math.ceil(Math.sqrt(diff1)) : 0;

        if (maxY < minY) continue; // 이 x에서는 유효한 점 없음

        if (x === 0) {
            // y축 위: (0, y), (0, -y) → 각 y마다 2개
            answer += 2 * (maxY - minY + 1);
        } else {
            if (minY === 0) {
                // y = 0: (±x, 0) → 2개
                answer += 2;
                // y >= 1: (±x, ±y) → y 하나당 4개
                answer += 4 * maxY;
            } else {
                // y >= minY: (±x, ±y) → y 하나당 4개
                answer += 4 * (maxY - minY + 1);
            }
        }
    }

    return answer;
}