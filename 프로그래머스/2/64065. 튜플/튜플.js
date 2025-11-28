function solution(s) {
    // 1. 바깥 {{ }} 제거
    s = s.slice(2, -2);

    // 2. "},{" 기준으로 분리
    const parts = s.split("},{");

    // 3. 각 부분을 숫자 배열로 변환
    const list = parts.map(part => part.split(",").map(Number));

    // 4. 길이 기준 오름차순 정렬
    list.sort((a, b) => a.length - b.length);

    const answer = [];
    const seen = new Set();

    // 5. 작은 집합부터 새로운 숫자만 추가
    for (const subset of list) {
        for (const num of subset) {
            if (!seen.has(num)) {
                seen.add(num);
                answer.push(num);
            }
        }
    }

    return answer;
}
