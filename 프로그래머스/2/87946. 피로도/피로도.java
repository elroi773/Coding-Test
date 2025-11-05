class Solution {
    int maxCount = 0; // 최대 탐험 가능한 던전 수 저장

    public int solution(int k, int[][] dungeons) {
        boolean[] visited = new boolean[dungeons.length];
        dfs(k, dungeons, visited, 0);
        return maxCount;
    }

    // DFS 탐색
    void dfs(int fatigue, int[][] dungeons, boolean[] visited, int count) {
        // 현재까지 탐험한 수로 최대값 갱신
        maxCount = Math.max(maxCount, count);

        // 모든 던전에 대해 시도
        for (int i = 0; i < dungeons.length; i++) {
            int need = dungeons[i][0]; // 최소 필요 피로도
            int cost = dungeons[i][1]; // 소모 피로도

            // 방문하지 않았고 탐험 가능한 경우
            if (!visited[i] && fatigue >= need) {
                visited[i] = true; // 방문 표시
                dfs(fatigue - cost, dungeons, visited, count + 1); // 다음 단계 탐색
                visited[i] = false; // 백트래킹 (원복)
            }
        }
    }
}
