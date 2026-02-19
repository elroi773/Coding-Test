function solution(n, costs) {
  // Union-Find (DSU)
  const parent = Array.from({ length: n }, (_, i) => i);

  const find = (x) => {
    if (parent[x] === x) return x;
    parent[x] = find(parent[x]);
    return parent[x];
  };

  const union = (a, b) => {
    a = find(a);
    b = find(b);
    if (a === b) return false;
    parent[b] = a; // (랭크 최적화 없어도 n<=100이라 충분)
    return true;
  };

  // 비용 기준 정렬
  costs.sort((e1, e2) => e1[2] - e2[2]);

  let answer = 0;
  let picked = 0;

  for (const [u, v, w] of costs) {
    if (union(u, v)) {
      answer += w;
      picked += 1;
      if (picked === n - 1) break; // MST 완성
    }
  }

  return answer;
}