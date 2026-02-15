#include <string>
#include <vector>
using namespace std;

int solution(int m, int n, vector<vector<int>> puddles) {
    const int MOD = 1000000007;

    // puddles: (x, y) = (열, 행) 1-indexed
    vector<vector<bool>> blocked(n + 1, vector<bool>(m + 1, false));
    for (const auto& p : puddles) {
        int x = p[0], y = p[1];
        blocked[y][x] = true;
    }

    vector<vector<int>> dp(n + 1, vector<int>(m + 1, 0));
    dp[1][1] = 1;

    for (int y = 1; y <= n; y++) {
        for (int x = 1; x <= m; x++) {
            if (blocked[y][x]) {
                dp[y][x] = 0;
                continue;
            }
            if (x == 1 && y == 1) continue;

            long long ways = 0;
            ways += dp[y - 1][x]; // 위에서
            ways += dp[y][x - 1]; // 왼쪽에서
            dp[y][x] = (int)(ways % MOD);
        }
    }

    return dp[n][m];
}