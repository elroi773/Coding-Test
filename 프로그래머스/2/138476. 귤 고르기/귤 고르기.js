function solution(k, tangerine) {
    // 1️⃣ 크기별 개수 세기
    const countMap = new Map();
    for (const t of tangerine) {
        countMap.set(t, (countMap.get(t) || 0) + 1);
    }

    // 2️⃣ 개수만 뽑아서 내림차순 정렬
    const counts = Array.from(countMap.values()).sort((a, b) => b - a);

    // 3️⃣ 큰 개수부터 더하면서 k개 채우기
    let total = 0;
    let answer = 0;

    for (const c of counts) {
        total += c;
        answer++;
        if (total >= k) break;
    }

    return answer;
}
