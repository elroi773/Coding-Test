function solution(n) {
    let answer = 0;

    const col = Array(n).fill(false);
    const diag1 = Array(2 * n).fill(false);
    const diag2 = Array(2 * n).fill(false);

    function backtrack(row) {
        if (row === n) {
            answer++;
            return;
        }

        for (let c = 0; c < n; c++) {
            if (col[c] || diag1[row - c + n] || diag2[row + c]) continue;

            // 퀸 배치
            col[c] = diag1[row - c + n] = diag2[row + c] = true;

            backtrack(row + 1);

            // 백트래킹
            col[c] = diag1[row - c + n] = diag2[row + c] = false;
        }
    }

    backtrack(0);
    return answer;
}
