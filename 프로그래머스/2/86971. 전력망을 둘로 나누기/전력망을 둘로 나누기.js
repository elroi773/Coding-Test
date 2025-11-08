function solution(n, wires) {
    // 그래프(인접 리스트) 구성
    const graph = Array.from({ length: n + 1 }, () => []);

    for (const [a, b] of wires) {
        graph[a].push(b);
        graph[b].push(a);
    }

    // 연결된 노드 개수를 세는 함수 (BFS)
    const countNodes = (start, except) => {
        const visited = Array(n + 1).fill(false);
        const queue = [start];
        visited[start] = true;
        let count = 1;

        while (queue.length > 0) {
            const node = queue.shift();
            for (const next of graph[node]) {
                if (!visited[next] && next !== except) {
                    visited[next] = true;
                    queue.push(next);
                    count++;
                }
            }
        }
        return count;
    };

    let minDiff = n; // 가능한 최대 차이로 초기화

    // 각 간선을 하나씩 끊어서 확인
    for (const [a, b] of wires) {
        const countA = countNodes(a, b); // a 쪽 트리 크기
        const countB = n - countA;       // b 쪽 트리 크기
        const diff = Math.abs(countA - countB);
        minDiff = Math.min(minDiff, diff);
    }

    return minDiff;
}
