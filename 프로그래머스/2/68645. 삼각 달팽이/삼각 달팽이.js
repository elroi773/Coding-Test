function solution(n) {
    const total = (n * (n + 1)) / 2;      // 채워야 할 숫자 개수
    const answer = new Array(total);

    // 삼각 달팽이를 채울 임시 2차원 배열
    const tri = Array.from({ length: n }, () => Array(n).fill(0));

    // 방향: 아래(0), 오른쪽(1), 왼쪽 위(2)
    const dr = [1, 0, -1];
    const dc = [0, 1, -1];

    let r = 0, c = 0, dir = 0;

    for (let num = 1; num <= total; num++) {
        tri[r][c] = num;

        let nr = r + dr[dir];
        let nc = c + dc[dir];

        // 범위를 벗어나거나 이미 값이 있으면 방향 전환
        if (nr < 0 || nr >= n || nc < 0 || nc >= n || tri[nr][nc] !== 0) {
            dir = (dir + 1) % 3;
            nr = r + dr[dir];
            nc = c + dc[dir];
        }

        r = nr;
        c = nc;
    }

    // 삼각형 부분만 위→아래, 왼→오 순으로 answer에 담기
    let idx = 0;
    for (let i = 0; i < n; i++) {
        for (let j = 0; j <= i; j++) {
            answer[idx++] = tri[i][j];
        }
    }

    return answer;
}