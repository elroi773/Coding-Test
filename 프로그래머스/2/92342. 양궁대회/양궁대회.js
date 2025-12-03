function solution(n, info) {
    const cur = Array(11).fill(0); // 현재 DFS에서의 라이언 배치
    let best = null;               // 최종 최적 배치
    let bestDiff = 0;              // 최대 점수 차이

    function dfs(idx, arrowsLeft) {
        // 마지막 점수(0점, idx === 10) 처리
        if (idx === 10) {
            // 남은 화살 전부 0점에 사용
            cur[10] = arrowsLeft;

            let rScore = 0;
            let aScore = 0;

            // 점수 계산
            for (let i = 0; i < 11; i++) {
                if (info[i] === 0 && cur[i] === 0) continue;

                const point = 10 - i;
                if (cur[i] > info[i]) {
                    rScore += point;
                } else {
                    aScore += point;
                }
            }

            const diff = rScore - aScore;

            // 라이언이 이기지 못하면 버림
            if (diff <= 0) {
                cur[10] = 0; // 원상복구
                return;
            }

            // 더 큰 점수 차이면 무조건 갱신
            if (best === null || diff > bestDiff) {
                bestDiff = diff;
                best = cur.slice();
            }
            // 점수 차이가 같다면, 낮은 점수(뒤 인덱스) 많이 맞힌 경우 선택
            else if (diff === bestDiff) {
                for (let i = 10; i >= 0; i--) {
                    if (cur[i] > best[i]) {
                        best = cur.slice();
                        break;
                    } else if (cur[i] < best[i]) {
                        break;
                    }
                }
            }

            cur[10] = 0; // 원상복구
            return;
        }

        // 1) 현재 점수(10 - idx)를 따기 위해 어피치보다 1발 더 쏘는 경우
        const need = info[idx] + 1;
        if (need <= arrowsLeft) {
            cur[idx] = need;
            dfs(idx + 1, arrowsLeft - need);
            cur[idx] = 0; // 백트래킹
        }

        // 2) 현재 점수 포기 (0발 쏘는 경우)
        dfs(idx + 1, arrowsLeft);
    }

    dfs(0, n);

    // 이길 수 있는 경우가 없으면 [-1]
    if (best === null) {
        return [-1];
    }
    return best;
}