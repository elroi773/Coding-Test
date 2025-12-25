function solution(dice) {
    const n = dice.length;
    const half = n / 2;
    let maxWin = -1;
    let bestPick = [];

    function getDistribution(idxs) {
        let map = new Map();
        map.set(0, 1);

        for (const idx of idxs) {
            const next = new Map();
            for (const [sum, cnt] of map) {
                for (const face of dice[idx]) {
                    const ns = sum + face;
                    next.set(ns, (next.get(ns) || 0) + cnt);
                }
            }
            map = next;
        }
        return map;
    }

    function countWin(aDist, bDist) {
        let maxB = 0;
        for (const b of bDist.keys()) maxB = Math.max(maxB, b);

        const prefix = Array(maxB + 2).fill(0);
        for (const [b, cnt] of bDist) {
            prefix[b + 1] += cnt;
        }
        for (let i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1];
        }

        let win = 0;
        for (const [a, cntA] of aDist) {
            if (a <= maxB) win += cntA * prefix[a];
            else win += cntA * prefix[maxB + 1];
        }
        return win;
    }

    function dfs(idx, pick) {
        if (pick.length === half) {
            const aIdxs = pick;
            const bIdxs = [];
            for (let i = 0; i < n; i++) {
                if (!aIdxs.includes(i)) bIdxs.push(i);
            }

            const aDist = getDistribution(aIdxs);
            const bDist = getDistribution(bIdxs);
            const win = countWin(aDist, bDist);

            if (win > maxWin) {
                maxWin = win;
                bestPick = [...aIdxs];
            }
            return;
        }

        for (let i = idx; i < n; i++) {
            dfs(i + 1, [...pick, i]);
        }
    }

    dfs(0, []);

    // 1-based index로 변환
    return bestPick.map(v => v + 1);
}
