function solution(target) {
  const INF = 1e9;

  // throws: [score, singleOrBullCount]
  const throwsList = [];
  for (let i = 1; i <= 20; i++) {
    throwsList.push([i, 1]);       // single
    throwsList.push([i * 2, 0]);   // double
    throwsList.push([i * 3, 0]);   // triple
  }
  throwsList.push([50, 1]);        // bull

  const dpDarts = Array(target + 1).fill(INF);
  const dpSingles = Array(target + 1).fill(-INF);

  dpDarts[0] = 0;
  dpSingles[0] = 0;

  for (let t = 1; t <= target; t++) {
    for (const [score, sCnt] of throwsList) {
      if (t >= score && dpDarts[t - score] !== INF) {
        const candDarts = dpDarts[t - score] + 1;
        const candSingles = dpSingles[t - score] + sCnt;

        if (
          candDarts < dpDarts[t] ||
          (candDarts === dpDarts[t] && candSingles > dpSingles[t])
        ) {
          dpDarts[t] = candDarts;
          dpSingles[t] = candSingles;
        }
      }
    }
  }

  return [dpDarts[target], dpSingles[target]];
}