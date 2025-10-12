class MaxHeap {
  constructor() {
    this.heap = [];
  }

  push(value) {
    this.heap.push(value);
    this._bubbleUp();
  }

  pop() {
    if (this.heap.length === 0) return null;
    if (this.heap.length === 1) return this.heap.pop();
    const max = this.heap[0];
    this.heap[0] = this.heap.pop();
    this._sinkDown();
    return max;
  }

  _bubbleUp() {
    let index = this.heap.length - 1;
    const element = this.heap[index];
    while (index > 0) {
      const parentIdx = Math.floor((index - 1) / 2);
      const parent = this.heap[parentIdx];
      if (element <= parent) break;
      this.heap[index] = parent;
      this.heap[parentIdx] = element;
      index = parentIdx;
    }
  }

  _sinkDown() {
    let index = 0;
    const length = this.heap.length;
    const element = this.heap[0];

    while (true) {
      let leftIdx = index * 2 + 1;
      let rightIdx = index * 2 + 2;
      let left, right;
      let swap = null;

      if (leftIdx < length) {
        left = this.heap[leftIdx];
        if (left > element) swap = leftIdx;
      }

      if (rightIdx < length) {
        right = this.heap[rightIdx];
        if (
          (swap === null && right > element) ||
          (swap !== null && right > left)
        ) {
          swap = rightIdx;
        }
      }

      if (swap === null) break;
      this.heap[index] = this.heap[swap];
      this.heap[swap] = element;
      index = swap;
    }
  }
}

function solution(n, k, enemy) {
  const heap = new MaxHeap();

  for (let i = 0; i < enemy.length; i++) {
    n -= enemy[i];
    heap.push(enemy[i]);

    if (n < 0) {
      if (k > 0) {
        const largest = heap.pop(); // 가장 큰 라운드 무적권으로 막음
        n += largest;
        k--;
      } else {
        return i; // 막은 라운드 개수
      }
    }
  }
  return enemy.length;
}
