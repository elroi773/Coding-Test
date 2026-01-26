function solution(board, r, c) {
  const N = 4;
  const dr = [-1, 1, 0, 0];
  const dc = [0, 0, -1, 1];
  const idx = (rr, cc) => rr * 4 + cc;
  const inRange = (rr, cc) => rr >= 0 && rr < 4 && cc >= 0 && cc < 4;

  // 남은 카드 마스크 + 각 숫자(1~6)의 위치 2개 저장
  let mask = 0;
  const pos = Array.from({ length: 7 }, () => []);
  for (let i = 0; i < 4; i++) {
    for (let j = 0; j < 4; j++) {
      const v = board[i][j];
      if (v !== 0) {
        const p = idx(i, j);
        mask |= (1 << p);
        pos[v].push(p);
      }
    }
  }

  const types = [];
  for (let v = 1; v <= 6; v++) if (pos[v].length === 2) types.push(v);

  function ctrlMove(from, dir, curMask) {
    let rr = Math.floor(from / 4);
    let cc = from % 4;
    while (true) {
      const nr = rr + dr[dir];
      const nc = cc + dc[dir];
      if (!inRange(nr, nc)) break;
      rr = nr; cc = nc;
      const ni = idx(rr, cc);
      if (curMask & (1 << ni)) return ni; // 카드 발견
    }
    return idx(rr, cc); // 끝칸
  }

  function stepMove(from, dir) {
    const rr = Math.floor(from / 4);
    const cc = from % 4;
    const nr = rr + dr[dir];
    const nc = cc + dc[dir];
    if (!inRange(nr, nc)) return from;
    return idx(nr, nc);
  }

  // BFS: start -> target 최소 이동 횟수
  function dist(start, target, curMask) {
    if (start === target) return 0;
    const vis = Array(16).fill(-1);
    const q = Array(16);
    let head = 0, tail = 0;

    q[tail++] = start;
    vis[start] = 0;

    while (head < tail) {
      const u = q[head++];
      const d = vis[u];

      for (let dir = 0; dir < 4; dir++) {
        const v1 = stepMove(u, dir);
        if (vis[v1] === -1) {
          vis[v1] = d + 1;
          if (v1 === target) return vis[v1];
          q[tail++] = v1;
        }
        const v2 = ctrlMove(u, dir, curMask);
        if (vis[v2] === -1) {
          vis[v2] = d + 1;
          if (v2 === target) return vis[v2];
          q[tail++] = v2;
        }
      }
    }
    return vis[target];
  }

  // 메모: key = (mask<<4) | cursor
  const memo = new Map();
  const startCursor = idx(r, c);

  function dfs(curMask, cursor) {
    if (curMask === 0) return 0;
    const key = (curMask << 4) | cursor;
    if (memo.has(key)) return memo.get(key);

    let best = Infinity;

    for (const t of types) {
      const [p1, p2] = pos[t];
      const has1 = (curMask & (1 << p1)) !== 0;
      const has2 = (curMask & (1 << p2)) !== 0;
      if (!has1 && !has2) continue;

      const nextMask = curMask & ~(1 << p1) & ~(1 << p2);

      // 순서 1: cursor -> p1 (Enter) -> p2 (Enter)
      const cost1 = dist(cursor, p1, curMask) + 1 + dist(p1, p2, curMask) + 1;
      best = Math.min(best, cost1 + dfs(nextMask, p2));

      // 순서 2: cursor -> p2 (Enter) -> p1 (Enter)
      const cost2 = dist(cursor, p2, curMask) + 1 + dist(p2, p1, curMask) + 1;
      best = Math.min(best, cost2 + dfs(nextMask, p1));
    }

    memo.set(key, best);
    return best;
  }

  return dfs(mask, startCursor);
}