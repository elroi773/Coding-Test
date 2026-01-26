function solution(a, edges) {
  const n = a.length;

  let sum = 0n;
  for (let i = 0; i < n; i++) sum += BigInt(a[i]);
  if (sum !== 0n) return -1;

  // 인접 리스트 (head / to / next)
  const m = edges.length;
  const E = 2 * m;

  const head = new Int32Array(n);
  head.fill(-1);
  const to = new Int32Array(E);
  const next = new Int32Array(E);

  let idx = 0;
  for (let i = 0; i < m; i++) {
    const u = edges[i][0];
    const v = edges[i][1];

    to[idx] = v; next[idx] = head[u]; head[u] = idx++;
    to[idx] = u; next[idx] = head[v]; head[v] = idx++;
  }

  // parent + DFS order (stack)
  const parent = new Int32Array(n);
  parent.fill(-1);
  const order = new Int32Array(n);
  let ordSz = 0;

  const stack = new Int32Array(n);
  let top = 0;

  stack[top++] = 0;
  parent[0] = 0;

  while (top > 0) {
    const u = stack[--top];
    order[ordSz++] = u;

    for (let e = head[u]; e !== -1; e = next[e]) {
      const v = to[e];
      if (parent[v] === -1) {
        parent[v] = u;
        stack[top++] = v;
      }
    }
  }

  // 값은 BigInt로 관리 (오버플로 방지)
  const val = new Array(n);
  for (let i = 0; i < n; i++) val[i] = BigInt(a[i]);

  let ans = 0n;
  for (let i = ordSz - 1; i >= 1; i--) { // 루트(0) 제외
    const u = order[i];
    const x = val[u];
    ans += x < 0n ? -x : x;
    const p = parent[u];
    val[p] += x;
    val[u] = 0n;
  }

  // 프로그래머스는 Number로도 안전한 범위(문제 원본 기준)지만,
  // 혹시 큰 값이면 문자열로 반환 필요할 수 있음. 보통은 Number로 캐스팅.
  return Number(ans);
}