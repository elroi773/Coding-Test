function solution(N, road, K) {
    // 1. 그래프 생성
    const graph = Array.from({ length: N + 1 }, () => []);
    
    for (const [a, b, c] of road) {
        graph[a].push([b, c]);
        graph[b].push([a, c]);
    }
    
    // 2. 거리 배열 초기화
    const dist = Array(N + 1).fill(Infinity);
    dist[1] = 0;
    
    // 3. 우선순위 큐 (최소 힙)
    const pq = [];
    pq.push([0, 1]); // [거리, 마을]
    
    while (pq.length > 0) {
        // 거리 기준 오름차순 정렬
        pq.sort((a, b) => a[0] - b[0]);
        const [curDist, curNode] = pq.shift();
        
        if (curDist > dist[curNode]) continue;
        
        for (const [nextNode, cost] of graph[curNode]) {
            const newDist = curDist + cost;
            if (newDist < dist[nextNode]) {
                dist[nextNode] = newDist;
                pq.push([newDist, nextNode]);
            }
        }
    }
    
    // 4. K 이하로 도달 가능한 마을 수
    let answer = 0;
    for (let i = 1; i <= N; i++) {
        if (dist[i] <= K) answer++;
    }
    
    return answer;
}
