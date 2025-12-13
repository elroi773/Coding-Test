class MinHeap {
    constructor() {
        this.heap = [];
    }

    size() {
        return this.heap.length;
    }

    peek() {
        return this.heap[0];
    }

    push(value) {
        this.heap.push(value);
        let idx = this.heap.length - 1;

        while (idx > 0) {
            let parent = Math.floor((idx - 1) / 2);
            if (this.heap[parent] <= this.heap[idx]) break;

            [this.heap[parent], this.heap[idx]] = [this.heap[idx], this.heap[parent]];
            idx = parent;
        }
    }

    pop() {
        if (this.heap.length === 1) return this.heap.pop();

        const min = this.heap[0];
        this.heap[0] = this.heap.pop();

        let idx = 0;
        while (true) {
            let left = idx * 2 + 1;
            let right = idx * 2 + 2;
            let smallest = idx;

            if (left < this.heap.length && this.heap[left] < this.heap[smallest]) {
                smallest = left;
            }
            if (right < this.heap.length && this.heap[right] < this.heap[smallest]) {
                smallest = right;
            }
            if (smallest === idx) break;

            [this.heap[idx], this.heap[smallest]] = [this.heap[smallest], this.heap[idx]];
            idx = smallest;
        }

        return min;
    }
}

function solution(scoville, K) {
    const heap = new MinHeap();
    let count = 0;

    // 힙에 모든 값 삽입
    for (const s of scoville) {
        heap.push(s);
    }

    // 가장 작은 값이 K 이상이 될 때까지
    while (heap.peek() < K) {
        if (heap.size() < 2) return -1;

        const first = heap.pop();
        const second = heap.pop();
        const mixed = first + second * 2;

        heap.push(mixed);
        count++;
    }

    return count;
}
