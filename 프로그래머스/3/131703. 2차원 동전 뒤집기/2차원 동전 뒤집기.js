function solution(beginning, target) {
  const R = beginning.length;
  const C = beginning[0].length;

  // diff = beginning XOR target
  const diff = Array.from({ length: R }, (_, i) =>
    Array.from({ length: C }, (_, j) => beginning[i][j] ^ target[i][j])
  );

  let best = Infinity;
  const totalMasks = 1 << R;

  for (let mask = 0; mask < totalMasks; mask++) {
    // rowFlip(i) = (mask >> i) & 1
    const rf0 = mask & 1;

    // colFlip은 0행을 0으로 만들도록 자동 결정
    const colFlip = new Array(C).fill(0);
    let colCount = 0;
    for (let j = 0; j < C; j++) {
      colFlip[j] = diff[0][j] ^ rf0;
      colCount += colFlip[j];
    }

    // 전체 검증
    let ok = true;
    for (let i = 0; i < R && ok; i++) {
      const rfi = (mask >> i) & 1;
      for (let j = 0; j < C; j++) {
        if ((diff[i][j] ^ rfi ^ colFlip[j]) !== 0) {
          ok = false;
          break;
        }
      }
    }

    if (ok) {
      const rowCount = bitCount(mask);
      best = Math.min(best, rowCount + colCount);
    }
  }

  return best === Infinity ? -1 : best;
}

function bitCount(x) {
  let c = 0;
  while (x) {
    x &= x - 1;
    c++;
  }
  return c;
}