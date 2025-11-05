function solution(line) {
    const points = [];

    // 1️⃣ 모든 직선 쌍의 교점 계산
    for (let i = 0; i < line.length; i++) {
        const [A, B, E] = line[i];
        for (let j = i + 1; j < line.length; j++) {
            const [C, D, F] = line[j];

            const denom = A * D - B * C; // 분모
            if (denom === 0) continue;   // 평행 또는 일치

            const x = (B * F - E * D) / denom;
            const y = (E * C - A * F) / denom;

            // 정수 좌표만 저장
            if (Number.isInteger(x) && Number.isInteger(y)) {
                points.push([x, y]);
            }
        }
    }

    // 2️⃣ 최소/최대 좌표 찾기
    let minX = Infinity, maxX = -Infinity, minY = Infinity, maxY = -Infinity;
    for (const [x, y] of points) {
        minX = Math.min(minX, x);
        maxX = Math.max(maxX, x);
        minY = Math.min(minY, y);
        maxY = Math.max(maxY, y);
    }

    const width = maxX - minX + 1;
    const height = maxY - minY + 1;

    // 3️⃣ 격자 초기화
    const grid = Array.from({ length: height }, () => Array(width).fill('.'));

    // 4️⃣ 별 찍기
    for (const [x, y] of points) {
        const gridX = x - minX;
        const gridY = maxY - y; // y축 반전
        grid[gridY][gridX] = '*';
    }

    // 5️⃣ 문자열 배열로 변환
    const answer = grid.map(row => row.join(''));

    return answer;
}
