function solution(alp, cop, problems) {
  let maxAlp = 0, maxCop = 0;
  for (const [aReq, cReq] of problems) {
    if (aReq > maxAlp) maxAlp = aReq;
    if (cReq > maxCop) maxCop = cReq;
  }

  alp = Math.min(alp, maxAlp);
  cop = Math.min(cop, maxCop);

  const INF = 1e9;
  const dp = Array.from({ length: maxAlp + 1 }, () => Array(maxCop + 1).fill(INF));
  dp[alp][cop] = 0;

  for (let a = alp; a <= maxAlp; a++) {
    for (let c = cop; c <= maxCop; c++) {
      const cur = dp[a][c];
      if (cur === INF) continue;

      if (a + 1 <= maxAlp) dp[a + 1][c] = Math.min(dp[a + 1][c], cur + 1);
      if (c + 1 <= maxCop) dp[a][c + 1] = Math.min(dp[a][c + 1], cur + 1);

      for (const [reqA, reqC, rwdA, rwdC, cost] of problems) {
        if (a >= reqA && c >= reqC) {
          const na = Math.min(maxAlp, a + rwdA);
          const nc = Math.min(maxCop, c + rwdC);
          dp[na][nc] = Math.min(dp[na][nc], cur + cost);
        }
      }
    }
  }

  return dp[maxAlp][maxCop];
}