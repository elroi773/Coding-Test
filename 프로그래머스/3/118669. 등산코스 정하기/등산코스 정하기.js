function solution(n, paths, gates, summits) {
  const g = Array.from({ length: n + 1 }, () => []);
  for (const [a, b, w] of paths) {
    g[a].push([b, w]);
    g[b].push([a, w]);
  }

  const isGate = Array(n + 1).fill(false);
  const isSummit = Array(n + 1).fill(false);
  for (const x of gates) isGate[x] = true;
  for (const x of summits) isSummit[x] = true;

  const INF = 1e9;
  const dist = Array(n + 1).fill(INF);

  // MinHeap
  class MinHeap {
    constructor() { this.a = []; }
    push(x) {
      this.a.push(x);
      let i = this.a.length - 1;
      while (i > 0) {
        const p = (i - 1) >> 1;
        if (this.a[p][0] <= this.a[i][0]) break;
        [this.a[p], this.a[i]] = [this.a[i], this.a[p]];
        i = p;
      }
    }
    pop() {
      if (this.a.length === 0) return null;
      const top = this.a[0];
      const last = this.a.pop();
      if (this.a.length) {
        this.a[0] = last;
        let i = 0;
        while (true) {
          let l = i * 2 + 1, r = l + 1, m = i;
          if (l < this.a.length && this.a[l][0] < this.a[m][0]) m = l;
          if (r < this.a.length && this.a[r][0] < this.a[m][0]) m = r;
          if (m === i) break;
          [this.a[m], this.a[i]] = [this.a[i], this.a[m]];
          i = m;
        }
      }
      return top;
    }
    get size() { return this.a.length; }
  }

  const pq = new MinHeap();
  for (const gate of gates) {
    dist[gate] = 0;
    pq.push([0, gate]); // [intensity, node]
  }

  let bestIntensity = INF;

  while (pq.size) {
    const [inten, u] = pq.pop();
    if (inten !== dist[u]) continue;
    if (inten > bestIntensity) break;

    if (isSummit[u]) {
      if (inten < bestIntensity) bestIntensity = inten;
      continue;
    }

    for (const [v, w] of g[u]) {
      if (isGate[v]) continue;
      const ni = Math.max(inten, w);
      if (ni < dist[v]) {
        dist[v] = ni;
        pq.push([ni, v]);
      }
    }
  }

  summits.sort((a, b) => a - b);
  let ansSummit = summits[0];
  let ansInten = dist[ansSummit];
  for (const s of summits) {
    if (dist[s] < ansInten) {
      ansInten = dist[s];
      ansSummit = s;
    }
  }
  return [ansSummit, ansInten];
}