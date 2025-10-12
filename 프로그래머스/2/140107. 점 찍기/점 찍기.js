function solution(k, d) {
    let answer = 0;

    for (let x = 0; x <= d; x += k) {
        // 가능한 y의 최대 거리 계산
        const maxY = Math.floor(Math.sqrt(d * d - x * x));
        // y는 0, k, 2k, ... maxY 이하이므로 개수는 (maxY / k) + 1
        answer += Math.floor(maxY / k) + 1;
    }

    return answer;
}
