function solution(board) {
  const n = board.length;

  // padding with walls (1) to avoid bounds checks
  const b = Array.from({ length: n + 2 }, () => Array(n + 2).fill(1));
  for (let i = 0; i < n; i++) {
    for (let j = 0; j < n; j++) b[i + 1][j + 1] = board[i][j];
  }

  // normalize state so (r1,c1) <= (r2,c2) lexicographically
  function norm(r1, c1, r2, c2) {
    if (r1 < r2 || (r1 === r2 && c1 <= c2)) return [r1, c1, r2, c2];
    return [r2, c2, r1, c1];
  }

  // visited key
  function key(r1, c1, r2, c2) {
    return `${r1},${c1},${r2},${c2}`;
  }

  function isGoal(r1, c1, r2, c2) {
    return (r1 === n && c1 === n) || (r2 === n && c2 === n);
  }

  const start = norm(1, 1, 1, 2);
  const q = [[...start, 0]]; // r1,c1,r2,c2,time
  let head = 0;

  const visited = new Set();
  visited.add(key(...start));

  const dr = [-1, 1, 0, 0];
  const dc = [0, 0, -1, 1];

  while (head < q.length) {
    const [r1, c1, r2, c2, t] = q[head++];

    if (isGoal(r1, c1, r2, c2)) return t;

    // 1) move 4 directions
    for (let i = 0; i < 4; i++) {
      const nr1 = r1 + dr[i], nc1 = c1 + dc[i];
      const nr2 = r2 + dr[i], nc2 = c2 + dc[i];
      if (b[nr1][nc1] === 0 && b[nr2][nc2] === 0) {
        const nxt = norm(nr1, nc1, nr2, nc2);
        const k = key(...nxt);
        if (!visited.has(k)) {
          visited.add(k);
          q.push([...nxt, t + 1]);
        }
      }
    }

    // 2) rotations
    // horizontal (same row)
    if (r1 === r2) {
      for (const dir of [-1, 1]) { // up / down
        if (b[r1 + dir][c1] === 0 && b[r2 + dir][c2] === 0) {
          // pivot at (r1,c1)
          {
            const nxt = norm(r1, c1, r1 + dir, c1);
            const k = key(...nxt);
            if (!visited.has(k)) {
              visited.add(k);
              q.push([...nxt, t + 1]);
            }
          }
          // pivot at (r2,c2)
          {
            const nxt = norm(r2, c2, r2 + dir, c2);
            const k = key(...nxt);
            if (!visited.has(k)) {
              visited.add(k);
              q.push([...nxt, t + 1]);
            }
          }
        }
      }
    }

    // vertical (same column)
    if (c1 === c2) {
      for (const dir of [-1, 1]) { // left / right
        if (b[r1][c1 + dir] === 0 && b[r2][c2 + dir] === 0) {
          // pivot at (r1,c1)
          {
            const nxt = norm(r1, c1, r1, c1 + dir);
            const k = key(...nxt);
            if (!visited.has(k)) {
              visited.add(k);
              q.push([...nxt, t + 1]);
            }
          }
          // pivot at (r2,c2)
          {
            const nxt = norm(r2, c2, r2, c2 + dir);
            const k = key(...nxt);
            if (!visited.has(k)) {
              visited.add(k);
              q.push([...nxt, t + 1]);
            }
          }
        }
      }
    }
  }

  return -1; // unreachable by constraints (safety)
}