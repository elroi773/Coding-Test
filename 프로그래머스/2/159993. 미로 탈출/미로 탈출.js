function solution(maps) {
    const n = maps.length;
    const m = maps[0].length;

    let start, lever, exit;

    // 시작점, 레버, 출구 위치 찾기
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (maps[i][j] === 'S') start = [i, j];
            if (maps[i][j] === 'L') lever = [i, j];
            if (maps[i][j] === 'E') exit = [i, j];
        }
    }

    // BFS 함수: 시작점에서 목표까지 최소 거리 반환, 도달 불가 시 -1
    function bfs(start, target) {
        const queue = [];
        const visited = Array.from({length: n}, () => Array(m).fill(false));
        const directions = [[1,0], [-1,0], [0,1], [0,-1]];

        queue.push([...start, 0]);
        visited[start[0]][start[1]] = true;

        while (queue.length > 0) {
            const [x, y, dist] = queue.shift();

            if (x === target[0] && y === target[1]) return dist;

            for (const [dx, dy] of directions) {
                const nx = x + dx;
                const ny = y + dy;

                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && maps[nx][ny] !== 'X') {
                    visited[nx][ny] = true;
                    queue.push([nx, ny, dist + 1]);
                }
            }
        }
        return -1;
    }

    // S -> L 거리
    const distToLever = bfs(start, lever);
    if (distToLever === -1) return -1;

    // L -> E 거리
    const distToExit = bfs(lever, exit);
    if (distToExit === -1) return -1;

    return distToLever + distToExit;
}

// 테스트
console.log(solution(["SOOOL","XXXXO","OOOOO","OXXXX","OOOOE"])); // 16
console.log(solution(["LOOXS","OOOOX","OOOOO","OOOOO","EOOOO"])); // -1
