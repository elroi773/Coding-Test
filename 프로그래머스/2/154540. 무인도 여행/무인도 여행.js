function solution(maps) {
    const n = maps.length;
    const m = maps[0].length;
    const visited = Array.from({ length: n }, () => Array(m).fill(false));
    const result = [];

    const dx = [-1, 1, 0, 0];
    const dy = [0, 0, -1, 1];

    function dfs(x, y) {
        // 현재 위치의 숫자를 식량 값으로 변환
        let sum = Number(maps[x][y]);
        visited[x][y] = true;

        for (let dir = 0; dir < 4; dir++) {
            const nx = x + dx[dir];
            const ny = y + dy[dir];

            // 범위 안이고, 방문 안 했고, 바다가 아닐 때만 탐색
            if (
                nx >= 0 && nx < n &&
                ny >= 0 && ny < m &&
                !visited[nx][ny] &&
                maps[nx][ny] !== 'X'
            ) {
                sum += dfs(nx, ny); // 연결된 섬의 식량 합산
            }
        }
        return sum;
    }

    // 모든 좌표 탐색
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (!visited[i][j] && maps[i][j] !== 'X') {
                result.push(dfs(i, j));
            }
        }
    }

    // 결과 처리
    if (result.length === 0) return [-1];
    return result.sort((a, b) => a - b);
}
