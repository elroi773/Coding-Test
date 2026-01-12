function solution(clockHands) {
  const n = clockHands.length;
  let best = Infinity;

  const press = (b, r, c, t) => {
    if (t === 0) return;
    b[r][c] = (b[r][c] + t) & 3;
    if (r > 0) b[r - 1][c] = (b[r - 1][c] + t) & 3;
    if (r < n - 1) b[r + 1][c] = (b[r + 1][c] + t) & 3;
    if (c > 0) b[r][c - 1] = (b[r][c - 1] + t) & 3;
    if (c < n - 1) b[r][c + 1] = (b[r][c + 1] + t) & 3;
  };

  // 4^n = 2^(2n) : 첫 행 각 칸(0~3회)을 2비트씩
  const cases = 1 << (2 * n);

  for (let mask = 0; mask < cases; mask++) {
    // board 복사
    const b = clockHands.map((row) => row.slice());

    let cnt = 0;
    let tmp = mask;

    // 1) 첫 행 조작 적용
    for (let c = 0; c < n; c++) {
      const t = tmp & 3; // 0~3
      tmp >>= 2;
      cnt += t;
      press(b, 0, c, t);
    }

    // 2) 2행~n행: 윗칸을 0으로 만들기 위한 조작은 강제
    for (let r = 1; r < n; r++) {
      for (let c = 0; c < n; c++) {
        const above = b[r - 1][c];
        if (above !== 0) {
          const t = (4 - above) & 3; // 1~3
          cnt += t;
          press(b, r, c, t);
        }
      }
    }

    // 3) 마지막 행이 모두 0이면 성공
    let ok = true;
    for (let c = 0; c < n; c++) {
      if (b[n - 1][c] !== 0) {
        ok = false;
        break;
      }
    }

    if (ok) best = Math.min(best, cnt);
  }

  return best;
}