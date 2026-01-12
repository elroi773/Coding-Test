function solution(n, roads, sources, destination) {
  // 1) 인접 리스트
  const graph = Array.from({ length: n + 1 }, () => []);
  for (const [a, b] of roads) {
    graph[a].push(b);
    graph[b].push(a);
  }

  // 2) destination 기준 BFS
  const dist = Array(n + 1).fill(-1);
  dist[destination] = 0;

  const q = new Array(n);
  let head = 0,
    tail = 0;
  q[tail++] = destination;

  while (head < tail) {
    const cur = q[head++];
    const nextDist = dist[cur] + 1;

    for (const nxt of graph[cur]) {
      if (dist[nxt] === -1) {
        dist[nxt] = nextDist;
        q[tail++] = nxt;
      }
    }
  }

  // 3) sources 순서대로 결과
  return sources.map((s) => dist[s]);
}