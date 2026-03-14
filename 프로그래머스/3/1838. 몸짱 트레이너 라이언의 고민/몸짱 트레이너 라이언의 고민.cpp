#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

struct VectorHash {
    size_t operator()(const vector<int>& v) const {
        size_t h = v.size();
        for (int x : v) {
            h ^= (size_t)x + 0x9e3779b9 + (h << 6) + (h >> 2);
        }
        return h;
    }
};

// 같은 행에서 선택된 열들이 모두 거리 d 이상인지 확인
bool validRowMask(int mask, int n, int d) {
    vector<int> cols;
    for (int c = 0; c < n; c++) {
        if (mask & (1 << c)) cols.push_back(c);
    }

    for (int i = 0; i < (int)cols.size(); i++) {
        for (int j = i + 1; j < (int)cols.size(); j++) {
            if (cols[j] - cols[i] < d) return false;
        }
    }
    return true;
}

// 행 차이가 rowDiff일 때 prevMask와 curMask가 거리 d 조건을 만족하는지 확인
bool compatible(int prevMask, int curMask, int n, int d, int rowDiff) {
    int need = d - rowDiff;
    if (need <= 0) return true;

    vector<int> a, b;
    for (int c = 0; c < n; c++) {
        if (prevMask & (1 << c)) a.push_back(c);
        if (curMask & (1 << c)) b.push_back(c);
    }

    for (int x : a) {
        for (int y : b) {
            if (abs(x - y) < need) return false;
        }
    }
    return true;
}

// 최소 거리 d 이상으로 k개 배치 가능한지 판정
bool canPlace(int n, int k, int d) {
    if (k <= 1) return true;
    if (d <= 1) return k <= n * n;

    vector<int> masks;
    vector<int> bits;
    int totalMask = 1 << n;

    for (int mask = 0; mask < totalMask; mask++) {
        if (validRowMask(mask, n, d)) {
            masks.push_back(mask);
            bits.push_back(__builtin_popcount((unsigned)mask));
        }
    }

    int M = (int)masks.size();
    vector<int> maskToIdx(totalMask, -1);
    for (int i = 0; i < M; i++) maskToIdx[masks[i]] = i;

    int historyLen = min(n - 1, d - 1);

    // compat[rowDiff][prevIdx][curIdx]
    vector<vector<vector<char>>> compatTable(historyLen + 1,
        vector<vector<char>>(M, vector<char>(M, 1)));

    for (int diff = 1; diff <= historyLen; diff++) {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                compatTable[diff][i][j] = compatible(masks[i], masks[j], n, d, diff);
            }
        }
    }

    unordered_map<vector<int>, int, VectorHash> dp, ndp;
    dp[{}] = 0;

    for (int row = 0; row < n; row++) {
        ndp.clear();

        for (auto& entry : dp) {
            const vector<int>& history = entry.first;
            int cnt = entry.second;

            for (int curIdx = 0; curIdx < M; curIdx++) {
                int curMask = masks[curIdx];
                bool ok = true;

                for (int t = 1; t <= (int)history.size(); t++) {
                    int prevMask = history[(int)history.size() - t];
                    int prevIdx = maskToIdx[prevMask];
                    if (!compatTable[t][prevIdx][curIdx]) {
                        ok = false;
                        break;
                    }
                }

                if (!ok) continue;

                vector<int> nextHistory = history;
                nextHistory.push_back(curMask);
                if ((int)nextHistory.size() > historyLen) {
                    nextHistory.erase(nextHistory.begin());
                }

                int nextCnt = cnt + bits[curIdx];
                auto it = ndp.find(nextHistory);
                if (it == ndp.end() || it->second < nextCnt) {
                    ndp[nextHistory] = nextCnt;
                }
            }
        }

        dp.swap(ndp);
    }

    int best = 0;
    for (auto& entry : dp) best = max(best, entry.second);
    return best >= k;
}

int solution(int n, int m, vector<vector<int>> timetable) {
    // 최대 동시 이용자 수 계산 (퇴실 시각과 같은 입실 시각도 겹침)
    vector<int> diff(1322 + 2, 0);

    for (auto& t : timetable) {
        int s = t[0];
        int e = t[1];
        diff[s] += 1;
        diff[e + 1] -= 1;
    }

    int cur = 0, maxOverlap = 0;
    for (int time = 600; time <= 1321; time++) {
        cur += diff[time];
        maxOverlap = max(maxOverlap, cur);
    }

    if (maxOverlap <= 1) return 0;

    // 가능한 최소 거리의 최댓값 찾기
    for (int d = 2 * (n - 1); d >= 1; d--) {
        if (canPlace(n, maxOverlap, d)) {
            return d;
        }
    }

    return 0;
}