#include <vector>
#include <algorithm>

using namespace std;

int getSum(const vector<vector<int>>& ps, int x, int y) {
    if (x < 0 || y < 0) return 0;
    return ps[x][y];
}

int query(const vector<vector<int>>& ps, int x1, int y1, int x2, int y2) {
    return getSum(ps, x2, y2)
         - getSum(ps, x1 - 1, y2)
         - getSum(ps, x2, y1 - 1)
         + getSum(ps, x1 - 1, y1 - 1);
}

int solution(int n, vector<vector<int>> data) {
    int answer = 0;

    vector<int> xs, ys;
    xs.reserve(n);
    ys.reserve(n);

    for (int i = 0; i < n; i++) {
        xs.push_back(data[i][0]);
        ys.push_back(data[i][1]);
    }

    // 좌표 압축 준비
    sort(xs.begin(), xs.end());
    xs.erase(unique(xs.begin(), xs.end()), xs.end());

    sort(ys.begin(), ys.end());
    ys.erase(unique(ys.begin(), ys.end()), ys.end());

    int xSize = xs.size();
    int ySize = ys.size();

    // 각 점의 압축 좌표 저장
    vector<int> cx(n), cy(n);

    // 1-based index로 사용
    vector<vector<int>> ps(xSize + 1, vector<int>(ySize + 1, 0));

    for (int i = 0; i < n; i++) {
        cx[i] = lower_bound(xs.begin(), xs.end(), data[i][0]) - xs.begin() + 1;
        cy[i] = lower_bound(ys.begin(), ys.end(), data[i][1]) - ys.begin() + 1;
        ps[cx[i]][cy[i]] = 1;
    }

    // 2차원 누적합
    for (int x = 1; x <= xSize; x++) {
        for (int y = 1; y <= ySize; y++) {
            ps[x][y] += ps[x - 1][y] + ps[x][y - 1] - ps[x - 1][y - 1];
        }
    }

    // 모든 쌍 검사
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            // 넓이 0인 경우 제외
            if (cx[i] == cx[j] || cy[i] == cy[j]) continue;

            int x1 = min(cx[i], cx[j]);
            int x2 = max(cx[i], cx[j]);
            int y1 = min(cy[i], cy[j]);
            int y2 = max(cy[i], cy[j]);

            // 내부 영역이 없는 경우
            if (x1 + 1 > x2 - 1 || y1 + 1 > y2 - 1) {
                answer++;
                continue;
            }

            // 내부에 다른 점이 있는지 확인
            int inside = query(ps, x1 + 1, y1 + 1, x2 - 1, y2 - 1);
            if (inside == 0) {
                answer++;
            }
        }
    }

    return answer;
}