#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int solution(int n, vector<vector<int>> edge) {
    // 1) 인접 리스트
    vector<vector<int>> g(n + 1);
    g.reserve(n + 1);
    for (auto &e : edge) {
        int a = e[0], b = e[1];
        g[a].push_back(b);
        g[b].push_back(a);
    }

    // 2) 거리 배열 (-1: 미방문)
    vector<int> dist(n + 1, -1);
    queue<int> q;
    dist[1] = 0;
    q.push(1);

    // 3) BFS
    while (!q.empty()) {
        int cur = q.front(); q.pop();
        for (int nxt : g[cur]) {
            if (dist[nxt] == -1) {
                dist[nxt] = dist[cur] + 1;
                q.push(nxt);
            }
        }
    }

    // 4) 최대 거리 찾고 개수 세기
    int maxDist = 0;
    for (int i = 1; i <= n; i++) {
        if (dist[i] > maxDist) maxDist = dist[i];
    }

    int answer = 0;
    for (int i = 1; i <= n; i++) {
        if (dist[i] == maxDist) answer++;
    }

    return answer;
}