function solution(info, query) {
    const db = {};

    // 1. info 전처리: 각 지원자에 대해 2^4 = 16가지 조합의 key에 점수 추가
    for (const line of info) {
        const [lang, job, career, food, scoreStr] = line.split(" ");
        const score = Number(scoreStr);
        const attrs = [lang, job, career, food];

        // 0 ~ 15 (2^4)
        for (let mask = 0; mask < 16; mask++) {
            const keyParts = [];
            for (let i = 0; i < 4; i++) {
                if (mask & (1 << i)) {
                    keyParts.push("-"); // 해당 조건 무시
                } else {
                    keyParts.push(attrs[i]);
                }
            }
            const key = keyParts.join(" ");
            if (!db[key]) db[key] = [];
            db[key].push(score);
        }
    }

    // 2. 각 key별 점수 리스트 정렬
    for (const key in db) {
        db[key].sort((a, b) => a - b);
    }

    const answer = [];

    // 3. query 처리
    for (const q of query) {
        // "java and backend and junior and pizza 100"
        // -> "java backend junior pizza 100"
        const cleaned = q.replace(/ and /g, " ");
        const parts = cleaned.split(" ");

        const key = parts.slice(0, 4).join(" ");
        const score = Number(parts[4]);

        const list = db[key] || [];

        // score 이상인 사람 수 = 전체 길이 - lowerBound 위치
        const idx = lowerBound(list, score);
        answer.push(list.length - idx);
    }

    return answer;
}

// list에서 target 이상이 처음 나오는 인덱스 (lower bound)
function lowerBound(arr, target) {
    let left = 0;
    let right = arr.length; // [left, right)

    while (left < right) {
        const mid = Math.floor((left + right) / 2);
        if (arr[mid] >= target) {
            right = mid;
        } else {
            left = mid + 1;
        }
    }
    return left;
}