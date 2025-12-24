function solution(nodes, edges) {
    const MAX = 1_000_000;

    // 인접 리스트
    const graph = Array.from({ length: MAX + 1 }, () => []);
    const degree = new Int32Array(MAX + 1);
    const visited = new Uint8Array(MAX + 1);

    // 간선 정보
    for (const [a, b] of edges) {
        graph[a].push(b);
        graph[b].push(a);
        degree[a]++;
        degree[b]++;
    }

    let oddEvenTree = 0;
    let reverseTree = 0;

    // BFS용 큐
    const queue = [];

    for (const start of nodes) {
        if (visited[start]) continue;

        let A = 0;
        let B = 0;

        queue.length = 0;
        queue.push(start);
        visited[start] = 1;

        let head = 0;
        while (head < queue.length) {
            const cur = queue[head++];

            if ((degree[cur] & 1) === (cur & 1)) A++;
            else B++;

            for (const next of graph[cur]) {
                if (!visited[next]) {
                    visited[next] = 1;
                    queue.push(next);
                }
            }
        }

        if (A === 1) oddEvenTree++;
        if (B === 1) reverseTree++;
    }

    return [oddEvenTree, reverseTree];
}
