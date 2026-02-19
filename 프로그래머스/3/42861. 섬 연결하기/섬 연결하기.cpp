#include <string>
#include <vector>
#include <algorithm>

using namespace std;

struct DSU {
    vector<int> p, r;
    DSU(int n) : p(n), r(n, 0) {
        for (int i = 0; i < n; i++) p[i] = i;
    }
    int find(int x) {
        if (p[x] == x) return x;
        return p[x] = find(p[x]);
    }
    bool unite(int a, int b) {
        a = find(a);
        b = find(b);
        if (a == b) return false;
        if (r[a] < r[b]) swap(a, b);
        p[b] = a;
        if (r[a] == r[b]) r[a]++;
        return true;
    }
};

int solution(int n, vector<vector<int>> costs) {
    // 비용 오름차순 정렬 (Kruskal)
    sort(costs.begin(), costs.end(),
         [](const vector<int>& a, const vector<int>& b) {
             return a[2] < b[2];
         });

    DSU dsu(n);
    int answer = 0;
    int picked = 0;

    for (const auto& e : costs) {
        int u = e[0], v = e[1], w = e[2];
        if (dsu.unite(u, v)) {
            answer += w;
            if (++picked == n - 1) break; // MST 완성
        }
    }

    return answer;
}