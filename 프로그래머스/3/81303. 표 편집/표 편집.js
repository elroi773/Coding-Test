function solution(n, k, cmd) {
  const prev = Array(n);
  const next = Array(n);
  const removed = Array(n).fill(false);

  for (let i = 0; i < n; i++) {
    prev[i] = i - 1;
    next[i] = i + 1;
  }
  next[n - 1] = -1;

  const stack = []; // [idx, prevIdx, nextIdx]
  let cur = k;

  for (const s of cmd) {
    const op = s[0];

    if (op === 'U') {
      let x = parseInt(s.slice(2), 10);
      while (x--) cur = prev[cur];

    } else if (op === 'D') {
      let x = parseInt(s.slice(2), 10);
      while (x--) cur = next[cur];

    } else if (op === 'C') {
      removed[cur] = true;
      const p = prev[cur];
      const nx = next[cur];
      stack.push([cur, p, nx]);

      // unlink
      if (p !== -1) next[p] = nx;
      if (nx !== -1) prev[nx] = p;

      // move selection
      cur = (nx !== -1) ? nx : p;

    } else { // 'Z'
      const [idx, p, nx] = stack.pop();
      removed[idx] = false;

      // relink
      if (p !== -1) next[p] = idx;
      if (nx !== -1) prev[nx] = idx;
      prev[idx] = p;
      next[idx] = nx;
    }
  }

  let ans = '';
  // n이 최대 1e6 이라 문자열 += 대신 배열/빌더가 안전
  const out = new Array(n);
  for (let i = 0; i < n; i++) out[i] = removed[i] ? 'X' : 'O';
  return out.join('');
}