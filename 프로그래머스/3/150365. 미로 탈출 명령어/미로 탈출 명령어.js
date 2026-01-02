function solution(n, m, x, y, r, c, k) {
  const dist = (a, b, c, d) => Math.abs(a - c) + Math.abs(b - d);

  const d0 = dist(x, y, r, c);
  if (d0 > k || ((k - d0) % 2) !== 0) return "impossible";

  let cx = x, cy = y;
  const dirs = [
    ['d',  1,  0],
    ['l',  0, -1],
    ['r',  0,  1],
    ['u', -1,  0],
  ];

  let ans = [];
  for (let step = 0; step < k; step++) {
    let moved = false;

    for (const [ch, dx, dy] of dirs) {
      const nx = cx + dx;
      const ny = cy + dy;

      if (nx < 1 || nx > n || ny < 1 || ny > m) continue;

      const rem = k - (step + 1);
      const nd = dist(nx, ny, r, c);

      if (nd <= rem && ((rem - nd) % 2) === 0) {
        ans.push(ch);
        cx = nx; cy = ny;
        moved = true;
        break;
      }
    }

    if (!moved) return "impossible";
  }

  return ans.join("");
}