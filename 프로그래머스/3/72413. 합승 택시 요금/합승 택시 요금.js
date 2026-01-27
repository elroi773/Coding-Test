function solution(n, s, a, b, fares) {
  const INF = Number.MAX_SAFE_INTEGER;

  // dist[i][j]
  const dist = Array.from({ length: n + 1 }, () => Array(n + 1).fill(INF));
  for (let i = 1; i <= n; i++) dist[i][i] = 0;

  // 양방향 간선
  for (const [c, d, f] of fares) {
    if (f < dist[c][d]) {
      dist[c][d] = f;
      dist[d][c] = f;
    }
  }

  // Floyd-Warshall
  for (let k = 1; k <= n; k++) {
    for (let i = 1; i <= n; i++) {
      const ik = dist[i][k];
      if (ik === INF) continue;
      for (let j = 1; j <= n; j++) {
        const nd = ik + dist[k][j];
        if (nd < dist[i][j]) dist[i][j] = nd;
      }
    }
  }

  let ans = INF;
  for (let k = 1; k <= n; k++) {
    const cost = dist[s][k] + dist[k][a] + dist[k][b];
    if (cost < ans) ans = cost;
  }

  return ans;
}