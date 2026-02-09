#include <string>
#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

static bool canCover(const vector<int>& segment, const vector<int>& friendsPrefix) {
    int usedCnt = 1;
    int coverageEnd = segment[0] + friendsPrefix[0];

    for (int w : segment) {
        if (w > coverageEnd) {
            usedCnt++;
            if (usedCnt > (int)friendsPrefix.size()) return false;
            coverageEnd = w + friendsPrefix[usedCnt - 1];
        }
    }
    return true;
}

int solution(int n, vector<int> weak, vector<int> dist) {
    int m = (int)weak.size();
    int dLen = (int)dist.size();

    // weak 확장 (원형 -> 선형)
    vector<int> extended = weak;
    extended.reserve(m * 2);
    for (int w : weak) extended.push_back(w + n);

    // dist 정렬(중복 순열 가지치기)
    sort(dist.begin(), dist.end());

    int best = numeric_limits<int>::max();

    // 순열 DFS (prefix 길이로 최소 친구 수 갱신)
    vector<bool> used(dLen, false);
    vector<int> perm(dLen, 0);

    function<void(int, const vector<int>&)> dfs = [&](int depth, const vector<int>& segment) {
        if (depth >= best) return; // 가지치기

        if (depth > 0) {
            vector<int> friendsPrefix(perm.begin(), perm.begin() + depth);
            if (canCover(segment, friendsPrefix)) {
                best = min(best, depth);
                return;
            }
        }

        if (depth == dLen) return;

        int prev = -1;
        for (int i = 0; i < dLen; i++) {
            if (used[i]) continue;
            if (prev != -1 && dist[i] == prev) continue; // 중복 제거
            prev = dist[i];

            used[i] = true;
            perm[depth] = dist[i];
            dfs(depth + 1, segment);
            used[i] = false;
        }
    };

    // 시작점을 weak의 각 지점으로 잡고 탐색
    for (int start = 0; start < m; start++) {
        vector<int> segment(extended.begin() + start, extended.begin() + start + m);
        fill(used.begin(), used.end(), false);
        dfs(0, segment);
    }

    return (best == numeric_limits<int>::max()) ? -1 : best;
}