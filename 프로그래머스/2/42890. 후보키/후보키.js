function solution(relation) {
    const rowLen = relation.length;      // 튜플(행) 개수
    const colLen = relation[0].length;   // 속성(열) 개수
    const candidates = [];               // 후보키 목록

    // 1) 가능한 모든 속성 조합(부분집합)을 비트마스크로 생성
    for (let bit = 1; bit < (1 << colLen); bit++) {
        const set = [];

        // 해당 비트 조합이 어떤 컬럼을 포함하는지 체크
        for (let col = 0; col < colLen; col++) {
            if (bit & (1 << col)) set.push(col);
        }

        // 2) 유일성 검사
        const tuples = new Set();
        for (let row = 0; row < rowLen; row++) {
            let key = set.map(col => relation[row][col]).join(',');
            tuples.add(key);
        }

        if (tuples.size !== rowLen) continue; // 유일성 실패

        // 3) 최소성 검사
        let minimal = true;
        for (const c of candidates) {
            // 기존 후보키와 비교: 기존 후보키가 현재 조합의 부분집합이면 최소성 위반
            if ((c & bit) === c) {
                minimal = false;
                break;
            }
        }

        if (minimal) candidates.push(bit);
    }

    return candidates.length;
}
