function solution(numbers) {
  const INF = 1e15;

  // digit -> (x,y)
  const x = Array(10).fill(0);
  const y = Array(10).fill(0);

  x[0] = 1; y[0] = 3;
  x[1] = 0; y[1] = 0;
  x[2] = 1; y[2] = 0;
  x[3] = 2; y[3] = 0;
  x[4] = 0; y[4] = 1;
  x[5] = 1; y[5] = 1;
  x[6] = 2; y[6] = 1;
  x[7] = 0; y[7] = 2;
  x[8] = 1; y[8] = 2;
  x[9] = 2; y[9] = 2;

  // dist: 이동만의 최단비용(같은 숫자는 0)
  const dist = Array.from({ length: 10 }, (_, i) =>
    Array.from({ length: 10 }, (_, j) => (i === j ? 0 : INF))
  );

  // 인접 간선: 상하좌우=2, 대각=3
  for (let a = 0; a < 10; a++) {
    for (let b = a + 1; b < 10; b++) {
      const dx = Math.abs(x[a] - x[b]);
      const dy = Math.abs(y[a] - y[b]);
      if (dx <= 1 && dy <= 1 && !(dx === 0 && dy === 0)) {
        const w = (dx === 0 || dy === 0) ? 2 : 3;
        dist[a][b] = w;
        dist[b][a] = w;
      }
    }
  }

  // Floyd-Warshall (10개라 매우 작음)
  for (let k = 0; k < 10; k++) {
    for (let i = 0; i < 10; i++) {
      for (let j = 0; j < 10; j++) {
        const nd = dist[i][k] + dist[k][j];
        if (nd < dist[i][j]) dist[i][j] = nd;
      }
    }
  }

  // cost[a][b] = a에서 b를 "누르는" 비용 (a==b면 1)
  const cost = Array.from({ length: 10 }, () => Array(10).fill(0));
  for (let a = 0; a < 10; a++) {
    for (let b = 0; b < 10; b++) {
      cost[a][b] = (a === b) ? 1 : dist[a][b];
    }
  }

  // dp[l][r] (l!=r): 현재 왼손=l, 오른손=r일 때 최소 비용
  let dp = Array.from({ length: 10 }, () => Array(10).fill(INF));
  dp[4][6] = 0; // 시작 위치

  for (let idx = 0; idx < numbers.length; idx++) {
    const d = numbers.charCodeAt(idx) - 48;

    const ndp = Array.from({ length: 10 }, () => Array(10).fill(INF));

    for (let l = 0; l < 10; l++) {
      for (let r = 0; r < 10; r++) {
        if (l === r) continue;
        const cur = dp[l][r];
        if (cur >= INF) continue;

        // 그 숫자 위에 손가락이 있으면 반드시 그 손가락으로 눌러야 함
        if (d === l) {
          ndp[l][r] = Math.min(ndp[l][r], cur + 1);
        } else if (d === r) {
          ndp[l][r] = Math.min(ndp[l][r], cur + 1);
        } else {
          // 왼손으로 누르기: (d, r)
          if (d !== r) ndp[d][r] = Math.min(ndp[d][r], cur + cost[l][d]);
          // 오른손으로 누르기: (l, d)
          if (d !== l) ndp[l][d] = Math.min(ndp[l][d], cur + cost[r][d]);
        }
      }
    }

    dp = ndp;
  }

  let ans = INF;
  for (let l = 0; l < 10; l++) {
    for (let r = 0; r < 10; r++) {
      if (l === r) continue;
      ans = Math.min(ans, dp[l][r]);
    }
  }

  return ans;
}