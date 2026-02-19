function solution(operations) {
  // (value, id)로 lazy deletion
  const minH = []; // min-heap
  const maxH = []; // max-heap
  const alive = new Array(operations.length).fill(false);

  let nextId = 0;
  let validCount = 0;

  const swap = (h, i, j) => ([h[i], h[j]] = [h[j], h[i]]);

  const push = (h, node, cmp) => {
    h.push(node);
    let i = h.length - 1;
    while (i > 0) {
      const p = (i - 1) >> 1;
      if (cmp(h[i], h[p])) {
        swap(h, i, p);
        i = p;
      } else break;
    }
  };

  const pop = (h, cmp) => {
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

  const peek = (h) => (h.length ? h[0] : null);

  const cmpMin = (a, b) => a[0] < b[0]; // value 작은 게 우선
  const cmpMax = (a, b) => a[0] > b[0]; // value 큰 게 우선

  const cleanTop = (h, cmp) => {
    while (h.length && !alive[h[0][1]]) pop(h, cmp);
  };

  for (const op of operations) {
    if (op[0] === "I") {
      const x = parseInt(op.slice(2), 10);
      const id = nextId++;
      alive[id] = true;
      push(minH, [x, id], cmpMin);
      push(maxH, [x, id], cmpMax);
      validCount++;
    } else { // "D"
      if (validCount === 0) continue;

      if (op[2] === "1") { // delete max
        cleanTop(maxH, cmpMax);
        const node = pop(maxH, cmpMax);
        if (node) {
          alive[node[1]] = false;
          validCount--;
        }
      } else { // delete min
        cleanTop(minH, cmpMin);
        const node = pop(minH, cmpMin);
        if (node) {
          alive[node[1]] = false;
          validCount--;
        }
      }
    }
  }

  if (validCount === 0) return [0, 0];

  cleanTop(minH, cmpMin);
  cleanTop(maxH, cmpMax);

  return [peek(maxH)[0], peek(minH)[0]];
}