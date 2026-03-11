function solution(n, works) {
    const total = works.reduce((sum, work) => sum + work, 0);
    if (total <= n) return 0;

    class MaxHeap {
        constructor() {
            this.heap = [];
        }

        push(value) {
            this.heap.push(value);
            this.bubbleUp();
        }

        pop() {
            if (this.heap.length === 1) return this.heap.pop();

            const max = this.heap[0];
            this.heap[0] = this.heap.pop();
            this.bubbleDown();
            return max;
        }

        bubbleUp() {
            let index = this.heap.length - 1;

            while (index > 0) {
                const parent = Math.floor((index - 1) / 2);

                if (this.heap[parent] >= this.heap[index]) break;

                [this.heap[parent], this.heap[index]] = [this.heap[index], this.heap[parent]];
                index = parent;
            }
        }

        bubbleDown() {
            let index = 0;
            const length = this.heap.length;

            while (true) {
                let largest = index;
                const left = index * 2 + 1;
                const right = index * 2 + 2;

                if (left < length && this.heap[left] > this.heap[largest]) {
                    largest = left;
                }

                if (right < length && this.heap[right] > this.heap[largest]) {
                    largest = right;
                }

                if (largest === index) break;

                [this.heap[index], this.heap[largest]] = [this.heap[largest], this.heap[index]];
                index = largest;
            }
        }
    }

    const heap = new MaxHeap();

    for (const work of works) {
        heap.push(work);
    }

    while (n-- > 0) {
        const max = heap.pop();
        heap.push(max - 1);
    }

    let answer = 0;

    for (const value of heap.heap) {
        answer += value * value;
    }

    return answer;
}