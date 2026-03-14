#include <vector>
#include <algorithm>

using namespace std;

// 전역 변수를 정의할 경우 함수 내에 초기화 코드를 꼭 작성해주세요.
int solution(int n, int m, vector<vector<int>> edge_list, int k, vector<int> gps_log) {
    const int INF = 1e9;

    vector<vector<int>> graph(n + 1);
    for (const auto& edge : edge_list) {
        int a = edge[0];
        int b = edge[1];
        graph[a].push_back(b);
        graph[b].push_back(a);
    }

    vector<vector<int>> dp(k, vector<int>(n + 1, INF));

    // 시작점은 바꿀 수 없음
    dp[0][gps_log[0]] = 0;

    for (int t = 1; t < k; t++) {
        for (int v = 1; v <= n; v++) {
            int minPrev = dp[t - 1][v]; // 같은 거점에 머무르기

            // 인접한 거점에서 v로 이동
            for (int prev : graph[v]) {
                minPrev = min(minPrev, dp[t - 1][prev]);
            }

            if (minPrev == INF) continue;

            dp[t][v] = minPrev + (gps_log[t] == v ? 0 : 1);
        }
    }

    int answer = dp[k - 1][gps_log[k - 1]];
    return (answer == INF ? -1 : answer);
}