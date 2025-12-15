function solution(clothes) {
    const map = {};

    // 종류별 개수 세기
    for (const [name, kind] of clothes) {
        map[kind] = (map[kind] || 0) + 1;
    }

    let answer = 1;

    // (개수 + 1)씩 곱하기
    for (const key in map) {
        answer *= (map[key] + 1);
    }

    // 아무것도 안 입은 경우 제외
    return answer - 1;
}
