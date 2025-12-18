function solution(m, n, board) {
    let answer = 0;

    // 문자열 → 2차원 배열
    let map = board.map(row => row.split(''));

    while (true) {
        let remove = Array.from({ length: m }, () => Array(n).fill(false));
        let count = 0;

        // 1️⃣ 2x2 같은 블록 찾기
        for (let i = 0; i < m - 1; i++) {
            for (let j = 0; j < n - 1; j++) {
                let cur = map[i][j];
                if (cur === '.') continue;

                if (
                    map[i][j + 1] === cur &&
                    map[i + 1][j] === cur &&
                    map[i + 1][j + 1] === cur
                ) {
                    remove[i][j] = true;
                    remove[i][j + 1] = true;
                    remove[i + 1][j] = true;
                    remove[i + 1][j + 1] = true;
                }
            }
        }

        // 2️⃣ 블록 제거
        for (let i = 0; i < m; i++) {
            for (let j = 0; j < n; j++) {
                if (remove[i][j]) {
                    map[i][j] = '.';
                    count++;
                }
            }
        }

        // 제거된 게 없으면 종료
        if (count === 0) break;
        answer += count;

        // 3️⃣ 중력 처리
        for (let j = 0; j < n; j++) {
            let empty = m - 1;
            for (let i = m - 1; i >= 0; i--) {
                if (map[i][j] !== '.') {
                    map[empty][j] = map[i][j];
                    if (empty !== i) map[i][j] = '.';
                    empty--;
                }
            }
        }
    }

    return answer;
}
