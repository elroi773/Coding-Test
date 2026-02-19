function solution(jobs) {
  const n = jobs.length;
  if (n === 0) return 0;

  // job: [req, dur, id]
  const arr = jobs.map(([s, l], id) => [s, l, id]);
  arr.sort((a, b) => {
    if (a[0] !== b[0]) return a[0] - b[0];   // req
    if (a[1] !== b[1]) return a[1] - b[1];   // dur
    return a[2] - b[2];                      // id
  });

  // Min-heap by (dur, req, id)
  const heap = [];
  const cmp = (x, y) => {
    if (x[1] !== y[1]) return x[1] < y[1];   // dur
    if (x[0] !== y[0]) return x[0] < y[0];   // req
    return x[2] < y[2];                      // id
  };
  const swap = (h, i, j) => ([h[i], h[j]] = [h[j], h[i]]);
  const push = (h, v) => {
    h.push(v);
    let i = h.length - 1;
    while (i > 0) {
      const p = (i - 1) >> 1;
      if (cmp(h[i], h[p])) swap(h, i, p), (i = p);
      else break;
    }
  };
  const pop = (h) => {
    if (h.length === 0) return null;
    if (h.length === 1) return h.pop();
    const top = h[0];
    h[0] = h.pop();
    let i = 0;
    while (true) {
      const l = i * 2 + 1;
      const r = l + 1;
      let best = i;
      if (l < h.length && cmp(h[l], h[best])) best = l;
      if (r < h.length && cmp(h[r], h[best])) best = r;
      if (best === i) break;
      swap(h, i, best);
      i = best;
    }
    return top;
  };

  let time = 0;
  let idx = 0;
  let done = 0;
  let total = 0;

  while (done < n) {
    // 현재 시각까지 요청된 작업들 push
    while (idx < n && arr[idx][0] <= time) {
      push(heap, arr[idx]);
      idx++;
    }

    if (heap.length === 0) {
      // 대기큐 비면 다음 요청 시각으로 점프
      time = Math.max(time, arr[idx][0]);
      continue;
    }

    const [req, dur] = pop(heap);
    time += dur;
    total += (time - req);
    done++;
  }

  return Math.floor(total / n);
}