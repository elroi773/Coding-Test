// ----- Min Heap (for end times) -----
class MinHeap {
  constructor() {
    this.arr = [];
  }
  size() {
    return this.arr.length;
  }
  peek() {
    return this.arr[0];
  }
  push(x) {
    const a = this.arr;
    a.push(x);
    let i = a.length - 1;
    while (i > 0) {
      const p = (i - 1) >> 1;
      if (a[p] <= a[i]) break;
      [a[p], a[i]] = [a[i], a[p]];
      i = p;
    }
  }
  pop() {
    const a = this.arr;
    const ret = a[0];
    const last = a.pop();
    if (a.length > 0) {
      a[0] = last;
      let i = 0;
      while (true) {
        const l = i * 2 + 1;
        const r = i * 2 + 2;
        let s = i;
        if (l < a.length && a[l] < a[s]) s = l;
        if (r < a.length && a[r] < a[s]) s = r;
        if (s === i) break;
        [a[i], a[s]] = [a[s], a[i]];
        i = s;
      }
    }
    return ret;
  }
}

function calcWait(reqList, m) {
  if (reqList.length === 0) return 0;

  const heap = new MinHeap();
  let total = 0;

  for (const [a, b] of reqList) {
    if (heap.size() < m) {
      heap.push(a + b);
    } else {
      const earliestEnd = heap.pop();
      const start = Math.max(a, earliestEnd);
      total += (start - a);
      heap.push(start + b);
    }
  }
  return total;
}

function solution(k, n, reqs) {
  // 1) split requests by type (reqs already sorted by time)
  const byType = Array.from({ length: k + 1 }, () => []);
  for (const [a, b, c] of reqs) {
    byType[c].push([a, b]);
  }

  // 2) precompute cost[type][mentors]
  const cost = Array.from({ length: k + 1 }, () => Array(n + 1).fill(0));
  for (let t = 1; t <= k; t++) {
    for (let m = 1; m <= n; m++) {
      cost[t][m] = calcWait(byType[t], m);
    }
  }

  // 3) DP distribute mentors: dp[i][j] = min wait using j mentors for first i types
  const INF = Number.MAX_SAFE_INTEGER;
  const dp = Array.from({ length: k + 1 }, () => Array(n + 1).fill(INF));
  dp[0][0] = 0;

  for (let i = 1; i <= k; i++) {
    for (let j = i; j <= n; j++) { // at least 1 per type => j>=i
      const maxM = j - (i - 1);
      for (let m = 1; m <= maxM; m++) {
        if (dp[i - 1][j - m] === INF) continue;
        dp[i][j] = Math.min(dp[i][j], dp[i - 1][j - m] + cost[i][m]);
      }
    }
  }

  return dp[k][n];
}