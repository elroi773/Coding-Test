function solution(s) {
    const len = s.length;

    // 길이가 1이면 압축 불가능 → 1
    if (len === 1) return 1;

    let answer = Infinity;

    // 자르는 단위 1 ~ len/2
    for (let cut = 1; cut <= Math.floor(len / 2); cut++) {
        let compressed = "";
        let prev = s.slice(0, cut);
        let count = 1;

        // cut 단위로 문자열 순회
        for (let i = cut; i < len; i += cut) {
            let chunk = s.slice(i, i + cut);

            if (chunk === prev) {
                count++;
            } else {
                compressed += (count > 1 ? count : "") + prev;
                prev = chunk;
                count = 1;
            }
        }

        // 마지막 조각 처리
        compressed += (count > 1 ? count : "") + prev;

        answer = Math.min(answer, compressed.length);
    }

    return answer;
}
