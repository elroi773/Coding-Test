function solution(board) {
    const n = board.length;
    const m = board[0].length;

    const visited = Array.from({ length: n }, () => Array(m).fill(false));

    let startX = 0, startY = 0, goalX = 0, goalY = 0;

    // 시작점(R), 목표점(G) 찾기
    for (let i = 0; i < n; i++) {
        for (let j = 0; j < m; j++) {
            if (board[i][j] === "R") {
                startX = i;
                startY = j;
            }
            if (board[i][j] === "G") {
                goalX = i;
                goalY = j;
            }
        }
    }

    const dx = [1, -1, 0, 0];
    const dy = [0, 0, 1, -1];

    const queue = [];
    queue.push([startX, startY, 0]);
    visited[startX][startY] = true;

    while (queue.length > 0) {
        const [x, y, cnt] = queue.shift();

        // 목표 도착
        if (x === goalX && y === goalY) return cnt;

        // 4방향으로 탐색
        for (let d = 0; d < 4; d++) {
            let nx = x;
            let ny = y;

            // 한 방향으로 끝까지 미끄러짐
            while (true) {
                let tx = nx + dx[d];
                let ty = ny + dy[d];

                if (tx < 0 || ty < 0 || tx >= n || ty >= m) break; // 보드 밖
                if (board[tx][ty] === "D") break; // 장애물

                nx = tx;
                ny = ty;
            }

            if (!visited[nx][ny]) {
                visited[nx][ny] = true;
                queue.push([nx, ny, cnt + 1]);
            }
        }
    }

    return -1; // 목표 도달 불가
}
