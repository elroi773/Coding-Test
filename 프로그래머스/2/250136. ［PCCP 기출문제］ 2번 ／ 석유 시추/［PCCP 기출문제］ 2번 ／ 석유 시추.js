function solution(land) {
    const n = land.length;        // 세로
    const m = land[0].length;     // 가로

    const visited = Array.from({ length: n }, () => Array(m).fill(false));
    const colTotal = Array(m).fill(0);   // 각 열에서 얻을 수 있는 석유량

    const dr = [1, -1, 0, 0];
    const dc = [0, 0, 1, -1];

    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            // 새로운 석유 덩어리 발견
            if (land[i][j] === 1 && !visited[i][j]) {
                const stack = [[i, j]];
                visited[i][j] = true;

                let size = 0;            // 덩어리 크기
                const cols = new Set();  // 이 덩어리가 차지하는 열들

                // DFS (스택)
                while (stack.length > 0) {
                    const [r, c] = stack.pop();
                    size++;
                    cols.add(c);  // 이 열을 지나감

                    for (let k = 0; k < 4; k++) {
                        const nr = r + dr[k];
                        const nc = c + dc[k];

                        if (nr < 0 || nr >= n || nc < 0 || nc >= m) continue;
                        if (!visited[nr][nc] && land[nr][nc] === 1) {
                            visited[nr][nc] = true;
                            stack.push([nr, nc]);
                        }
                    }
                }

                // 이 덩어리가 지나가는 모든 열에 덩어리 크기 더하기
                for (const c of cols) {
                    colTotal[c] += size;
                }
            }
        }
    }

    return Math.max(...colTotal);
}