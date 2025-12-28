function solution(scores) {
    const [wanhoA, wanhoB] = scores[0];
    const wanhoSum = wanhoA + wanhoB;

    // 근무태도 내림차순, 동료평가 오름차순
    scores.sort((a, b) => {
        if (a[0] !== b[0]) return b[0] - a[0];
        return a[1] - b[1];
    });

    let maxB = 0;
    let rank = 1;

    for (const [a, b] of scores) {
        // 지배당한 경우
        if (b < maxB) {
            if (a === wanhoA && b === wanhoB) {
                return -1;
            }
            continue;
        }

        maxB = b;

        if (a + b > wanhoSum) {
            rank++;
        }
    }

    return rank;
}
