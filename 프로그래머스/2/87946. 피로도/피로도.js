function solution(k, dungeons) {
    let maxCount = 0; // 최대 탐험 가능한 던전 수

    // DFS 함수 정의
    function dfs(fatigue, visited, count) {
        // 현재까지의 탐험 개수로 최대값 갱신
        maxCount = Math.max(maxCount, count);

        for (let i = 0; i < dungeons.length; i++) {
            const [need, cost] = dungeons[i];

            // 아직 방문 안했고, 최소 필요 피로도 충족 시 탐험 가능
            if (!visited[i] && fatigue >= need) {
                visited[i] = true; // 방문 표시
                dfs(fatigue - cost, visited, count + 1);
                visited[i] = false; // 백트래킹 (원상복구)
            }
        }
    }

    dfs(k, Array(dungeons.length).fill(false), 0);
    return maxCount;
}
