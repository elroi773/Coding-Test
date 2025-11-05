#include <stdio.h>
#include <stdbool.h>

// 전역 변수 (DFS에서 최대 던전 수 저장용)
int maxCount = 0;

// DFS 함수 정의
void dfs(int k, int** dungeons, bool* visited, int count, int dungeons_rows) {
    // 현재까지 탐험한 던전 수로 최대값 갱신
    if (count > maxCount) {
        maxCount = count;
    }

    // 모든 던전에 대해 탐색
    for (int i = 0; i < dungeons_rows; i++) {
        int need = dungeons[i][0]; // 최소 필요 피로도
        int cost = dungeons[i][1]; // 소모 피로도

        // 아직 방문하지 않았고, 피로도가 충분하다면 탐험 가능
        if (!visited[i] && k >= need) {
            visited[i] = true; // 방문 표시
            dfs(k - cost, dungeons, visited, count + 1, dungeons_rows);
            visited[i] = false; // 백트래킹 (원상복구)
        }
    }
}

// 메인 solution 함수
int solution(int k, int** dungeons, size_t dungeons_rows, size_t dungeons_cols) {
    bool visited[8] = {false}; // 던전 최대 8개이므로 크기 8로 선언
    maxCount = 0; // 전역 변수 초기화

    dfs(k, dungeons, visited, 0, dungeons_rows);

    return maxCount;
}
