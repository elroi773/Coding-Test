function solution(n, lighthouse) {
  // 인접 리스트
  const g = Array.from({ length: n + 1 }, () => []);
  for (const [a, b] of lighthouse) {
    g[a].push(b);
    g[b].push(a);
  }

  const parent = new Int32Array(n + 1);
  const order = new Int32Array(n);   // DFS 방문 순서
  const stack = new Int32Array(n);   // 반복 DFS 스택

  // 반복 DFS: parent 세팅 + order 만들기
  let top = 0;
  let idx = 0;
  stack[top++] = 1;
  parent[1] = -1;

  while (top > 0) {
    const u = stack[--top];
    order[idx++] = u;

    for (const v of g[u]) {
      if (v === parent[u]) continue;
      if (parent[v] !== 0) continue; // 이미 방문
      parent[v] = u;
      stack[top++] = v;
    }
  }

  // dp0[u]: u를 끔(선택X), dp1[u]: u를 켬(선택O)
  const dp0 = new Int32Array(n + 1);
  const dp1 = new Int32Array(n + 1);

  // 후위 순회 효과: order 역순으로 DP
  for (let i = n - 1; i >= 0; i--) {
    const u = order[i];

    let off = 0; // dp0
    let on = 1;  // dp1 (u 켬)

    for (const v of g[u]) {
      if (v === parent[u]) continue;
      off += dp1[v];                       // u off -> 자식은 반드시 on
      on += Math.min(dp0[v], dp1[v]);      // u on  -> 자식은 min 선택
    }

    dp0[u] = off;
    dp1[u] = on;
  }

  return Math.min(dp0[1], dp1[1]);
}