function solution(matrix_sizes) {
  const n = matrix_sizes.length;

  // 차원 배열 p (길이 n+1)
  // i번째 행렬: p[i] x p[i+1]
  const p = new Array(n + 1);
  p[0] = matrix_sizes[0][0];
  for (let i = 0; i < n; i++) {
    p[i + 1] = matrix_sizes[i][1];
  }

  const INF = Number.MAX_SAFE_INTEGER;
  const dp = Array.from({ length: n }, () => Array(n).fill(0));

  // 구간 길이 2부터 n까지
  for (let len = 2; len <= n; len++) {
    for (let i = 0; i + len - 1 < n; i++) {
      const j = i + len - 1;
      let best = INF;

      // 분할점 k: (i..k) * (k+1..j)
      for (let k = i; k < j; k++) {
        const cost = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
        if (cost < best) best = cost;
      }

      dp[i][j] = best;
    }
  }

  return dp[0][n - 1];
}