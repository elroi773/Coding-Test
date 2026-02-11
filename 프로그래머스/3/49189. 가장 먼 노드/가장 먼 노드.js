function solution(n, edge) {
  // 1) 인접 리스트
  const g = Array.from({ length: n + 1 }, () => []);
  for (const [a, b] of edge) {
    g[a].push(b);
    g[b].push(a);
  }

  // 2) 거리 배열 (-1: 미방문)
  const dist = Array(n + 1).fill(-1);

  // 3) BFS 큐 (shift 대신 head 인덱스 사용)
  const q = new Array(n);
  let head = 0, tail = 0;

  dist[1] = 0;
  q[tail++] = 1;

  while (head < tail) {
    const cur = q[head++];
    for (const nxt of g[cur]) {
      if (dist[nxt] === -1) {
        dist[nxt] = dist[cur] + 1;
        q[tail++] = nxt;
      }
    }
  }

  // 4) 최대 거리와 개수
  let maxDist = 0;
  for (let i = 1; i <= n; i++) {
    if (dist[i] > maxDist) maxDist = dist[i];
  }

  let answer = 0;
  for (let i = 1; i <= n; i++) {
    if (dist[i] === maxDist) answer++;
  }

  return answer;
}