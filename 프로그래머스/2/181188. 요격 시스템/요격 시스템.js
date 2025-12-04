function solution(targets) {
    // 끝나는 지점(e) 기준으로 오름차순 정렬
    targets.sort((a, b) => {
        if (a[1] === b[1]) {
            return a[0] - b[0]; // e가 같으면 s 기준 (선택 사항)
        }
        return a[1] - b[1];
    });

    let answer = 0;
    let last = -1; // 마지막 요격 미사일을 쏜 x 좌표 (논리적으로는 e 바로 앞)

    for (const [s, e] of targets) {
        // 현재 구간의 시작이 last 이상이면,
        // 기존 요격 미사일로는 커버 불가 → 새 미사일 발사
        if (s >= last) {
            answer++;
            last = e; // 이 구간의 끝점 바로 앞에 쏜 것으로 간주
        }
    }

    return answer;
}