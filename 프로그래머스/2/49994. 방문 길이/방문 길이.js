function solution(dirs) {
    const moves = {
        'U': [0, 1],
        'D': [0, -1],
        'R': [1, 0],
        'L': [-1, 0]
    };

    const visited = new Set();
    let x = 0, y = 0;

    for (let d of dirs) {
        const [dx, dy] = moves[d];
        const nx = x + dx;
        const ny = y + dy;

        // 경계 체크
        if (nx < -5 || nx > 5 || ny < -5 || ny > 5) {
            continue;
        }

        // 길 기록 (양방향 저장)
        const path = `${x},${y},${nx},${ny}`;
        const reversePath = `${nx},${ny},${x},${y}`;

        visited.add(path);
        visited.add(reversePath);

        // 위치 업데이트
        x = nx;
        y = ny;
    }

    // 길은 양방향으로 저장했으므로 2로 나눔
    return visited.size / 2;
}
