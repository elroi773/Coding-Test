function solution(grid) {
    const n = grid.length;
    const m = grid[0].length;
    
    // 방향: 상(0), 우(1), 하(2), 좌(3)
    const dx = [-1, 0, 1, 0];
    const dy = [0, 1, 0, -1];
    
    // 방문 체크 (행, 열, 방향)
    const visited = Array.from({ length: n }, () =>
        Array.from({ length: m }, () => Array(4).fill(false))
    );
    
    const result = [];

    // 모든 칸, 모든 방향에서 시도
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            for (let d = 0; d < 4; d++) {
                if (!visited[i][j][d]) {
                    let x = i, y = j, dir = d;
                    let count = 0;

                    while (!visited[x][y][dir]) {
                        visited[x][y][dir] = true;
                        count++;

                        // 다음 위치 (토러스 구조)
                        x = (x + dx[dir] + n) % n;
                        y = (y + dy[dir] + m) % m;

                        // 다음 칸에서 방향 전환
                        const cell = grid[x][y];
                        if (cell === 'L') dir = (dir + 3) % 4; // 왼쪽
                        else if (cell === 'R') dir = (dir + 1) % 4; // 오른쪽
                    }

                    result.push(count);
                }
            }
        }
    }

    // 오름차순 정렬 후 반환
    return result.sort((a, b) => a - b);
}
