function solution(storage, requests) {
    const n = storage.length;
    const m = storage[0].length;

    // 창고 상태를 2차원 배열로 복사 (빈 칸은 '.' 로 표시)
    const grid = storage.map(row => row.split(''));

    // 4방향 (상, 하, 좌, 우)
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];

    for (const req of requests) {
        const target = req[0];

        // 크레인 요청: 해당 알파벳 모두 제거
        if (req.length === 2) {
            for (let i = 0; i < n; i++) {
                for (let j = 0; j < m; j++) {
                    if (grid[i][j] === target) {
                        grid[i][j] = '.';
                    }
                }
            }
            continue;
        }

        // 지게차 요청: 외부와 연결된 빈칸을 BFS로 탐색 (확장 격자 사용)
        const H = n + 2;
        const W = m + 2;
        const visited = Array.from({ length: H }, () => Array(W).fill(false));

        const queue = [];
        let head = 0;

        // (0,0)은 완전 바깥 영역
        visited[0][0] = true;
        queue.push([0, 0]);

        while (head < queue.length) {
            const [r, c] = queue[head++];
            for (let d = 0; d < 4; d++) {
                const nr = r + dr[d];
                const nc = c + dc[d];

                if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                if (visited[nr][nc]) continue;

                // 실제 창고 범위인지 확인 (1..n, 1..m)
                if (1 <= nr && nr <= n && 1 <= nc && nc <= m) {
                    // 창고 안에서 빈칸이면 이동 가능
                    if (grid[nr - 1][nc - 1] === '.') {
                        visited[nr][nc] = true;
                        queue.push([nr, nc]);
                    }
                } else {
                    // 창고 밖은 항상 이동 가능
                    visited[nr][nc] = true;
                    queue.push([nr, nc]);
                }
            }
        }

        // 외부와 연결된 빈칸(또는 외부)에 인접한 target 컨테이너 제거
        for (let i = 0; i < n; i++) {
            for (let j = 0; j < m; j++) {
                if (grid[i][j] !== target) continue;

                const er = i + 1; // 확장 격자 기준 좌표
                const ec = j + 1;
                let removable = false;

                for (let d = 0; d < 4; d++) {
                    const nr = er + dr[d];
                    const nc = ec + dc[d];

                    if (nr < 0 || nr >= H || nc < 0 || nc >= W) continue;
                    if (visited[nr][nc]) {
                        removable = true;
                        break;
                    }
                }

                if (removable) {
                    grid[i][j] = '.';
                }
            }
        }
    }

    // 남은 컨테이너 개수 세기
    let answer = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (grid[i][j] !== '.') answer++;
        }
    }

    return answer;
}