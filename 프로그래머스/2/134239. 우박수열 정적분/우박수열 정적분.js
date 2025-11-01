function solution(k, ranges) {
    // 1️⃣ 우박수열 생성
    const seq = [k];
    while (k !== 1) {
        if (k % 2 === 0) k = k / 2;
        else k = k * 3 + 1;
        seq.push(k);
    }

    const n = seq.length - 1; // 구간 개수

    // 2️⃣ 각 구간별 사다리꼴 넓이 계산 + 누적합(prefix)
    const prefix = [0];
    for (let i = 0; i < n; i++) {
        const area = (seq[i] + seq[i + 1]) / 2;
        prefix.push(prefix[i] + area);
    }

    // 3️⃣ 각 구간별 정적분 결과 계산
    const answer = [];
    for (const [a, b] of ranges) {
        const right = n + b; // b는 0 이하의 수
        if (a > right || a < 0 || right < 0 || a > n) {
            answer.push(-1.0);
        } else {
            const result = prefix[right] - prefix[a];
            answer.push(result);
        }
    }

    return answer;
}
