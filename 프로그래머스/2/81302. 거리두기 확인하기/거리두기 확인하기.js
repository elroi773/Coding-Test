function solution(places) {
    const answer = [];

    // 상하좌우
    const dr = [-1, 1, 0, 0];
    const dc = [0, 0, -1, 1];

    for (let k = 0; k < places.length; k++) {
        const place = places[k];
        let ok = 1;

        for (let r = 0; r < 5; r++) {
            for (let c = 0; c < 5; c++) {
                if (place[r][c] !== 'P') continue;

                // 거리 1 체크
                for (let d = 0; d < 4; d++) {
                    const nr = r + dr[d];
                    const nc = c + dc[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        if (place[nr][nc] === 'P') ok = 0;
                    }
                }

                // 거리 2 체크
                const dr2 = [-2, 2, 0, 0];
                const dc2 = [0, 0, -2, 2];
                for (let d = 0; d < 4; d++) {
                    const nr = r + dr2[d];
                    const nc = c + dc2[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        const mr = (r + nr) / 2;
                        const mc = (c + nc) / 2;
                        if (place[nr][nc] === 'P' && place[mr][mc] !== 'X') ok = 0;
                    }
                }

                // 대각선 체크
                const drDiag = [-1, -1, 1, 1];
                const dcDiag = [-1, 1, -1, 1];
                for (let d = 0; d < 4; d++) {
                    const nr = r + drDiag[d];
                    const nc = c + dcDiag[d];
                    if (nr >= 0 && nr < 5 && nc >= 0 && nc < 5) {
                        if (place[nr][nc] === 'P') {
                            if (!(place[r][nc] === 'X' && place[nr][c] === 'X')) ok = 0;
                        }
                    }
                }
            }
        }

        answer.push(ok);
    }

    return answer;
}
