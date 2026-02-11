function solution(n, results) {
  // win[a][b] = a가 b를 이김
  const win = Array.from({ length: n + 1 }, () => Array(n + 1).fill(false));

  for (const [a, b] of results) {
    win[a][b] = true;
  }

  // Floyd-Warshall: a>k and k>b => a>b
  for (let k = 1; k <= n; k++) {
    for (let a = 1; a <= n; a++) {
      if (!win[a][k]) continue;
      for (let b = 1; b <= n; b++) {
        if (win[k][b]) win[a][b] = true;
      }
    }
  }

  let answer = 0;
  for (let i = 1; i <= n; i++) {
    let known = 0;
    for (let j = 1; j <= n; j++) {
      if (i === j) continue;
      if (win[i][j] || win[j][i]) known++;
    }
    if (known === n - 1) answer++;
  }

  return answer;
}