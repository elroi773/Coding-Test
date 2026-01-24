function solution(game_board, table) {
  const n = game_board.length;
  const dr = [1, -1, 0, 0];
  const dc = [0, 0, 1, -1];

  function extract(grid, target) {
    const visited = Array.from({ length: n }, () => Array(n).fill(false));
    const comps = [];

    for (let r = 0; r < n; r++) {
      for (let c = 0; c < n; c++) {
        if (visited[r][c] || grid[r][c] !== target) continue;

        visited[r][c] = true;
        const q = [[r, c]];
        let head = 0;
        const pts = [];

        while (head < q.length) {
          const [cr, cc] = q[head++];
          pts.push([cr, cc]);

          for (let k = 0; k < 4; k++) {
            const nr = cr + dr[k];
            const nc = cc + dc[k];
            if (nr < 0 || nr >= n || nc < 0 || nc >= n) continue;
            if (visited[nr][nc] || grid[nr][nc] !== target) continue;
            visited[nr][nc] = true;
            q.push([nr, nc]);
          }
        }

        comps.push(pts);
      }
    }
    return comps;
  }

  function normalize(pts) {
    let minR = Infinity, minC = Infinity;
    for (const [r, c] of pts) {
      if (r < minR) minR = r;
      if (c < minC) minC = c;
    }
    const norm = pts.map(([r, c]) => [r - minR, c - minC]);
    norm.sort((a, b) => (a[0] - b[0]) || (a[1] - b[1]));
    return norm;
  }

  // (r,c) -> (c, -r)
  function rotate90(pts) {
    return pts.map(([r, c]) => [c, -r]);
  }

  function keyOf(pts) {
    // pts는 normalize + 정렬된 상태라고 가정
    return pts.map(([r, c]) => `${r},${c}`).join(';');
  }

  // ✅ 4회전 중 최소 key를 canonical로 사용
  function canonicalKey(rawPts) {
    let pts = rawPts.slice(); // shallow copy ok (원소는 [r,c])
    let best = null;

    for (let i = 0; i < 4; i++) {
      const norm = normalize(pts);
      const k = keyOf(norm);
      if (best === null || k < best) best = k;

      // 다음 회전은 "정규화된 좌표" 기준으로 돌려야 안정적
      pts = rotate90(norm);
    }
    return best;
  }

  // table 블록 카운트
  const blocks = extract(table, 1);
  const blockCount = new Map();
  for (const b of blocks) {
    const k = canonicalKey(b);
    blockCount.set(k, (blockCount.get(k) || 0) + 1);
  }

  // game_board 구멍 매칭
  const holes = extract(game_board, 0);
  let filled = 0;

  for (const h of holes) {
    const hk = canonicalKey(h);
    const cnt = blockCount.get(hk) || 0;
    if (cnt > 0) {
      filled += h.length;
      if (cnt === 1) blockCount.delete(hk);
      else blockCount.set(hk, cnt - 1);
    }
  }

  return filled;
}