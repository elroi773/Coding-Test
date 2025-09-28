function solution(info, n, m) {
    const INF = 1e9;
    let dp = Array(m).fill(INF);
    dp[0] = 0;

    for (let [aCost, bCost] of info) {
        let newdp = Array(m).fill(INF);

        for (let b = 0; b < m; b++) {
            if (dp[b] === INF) continue;

            // A가 훔치는 경우
            let newA = dp[b] + aCost;
            if (newA < n) {
                newdp[b] = Math.min(newdp[b], newA);
            }

            // B가 훔치는 경우
            let newB = b + bCost;
            if (newB < m) {
                newdp[newB] = Math.min(newdp[newB], dp[b]);
            }
        }
        dp = newdp;
    }

    let ans = Math.min(...dp);
    return ans === INF ? -1 : ans;
}

// 예시
console.log(solution([[1,2],[2,3],[2,1]], 4, 4)); // 2
