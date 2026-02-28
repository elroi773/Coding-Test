#include <vector>
#include <algorithm>
#include <limits>

using namespace std;

int solution(vector<vector<int>> matrix_sizes) {
    int n = (int)matrix_sizes.size();

    // p: 행렬 체인의 차원 배열 (길이 n+1)
    vector<long long> p(n + 1);
    p[0] = matrix_sizes[0][0];
    for (int i = 0; i < n; i++) {
        p[i + 1] = matrix_sizes[i][1];
    }

    const long long INF = numeric_limits<long long>::max() / 4;

    // dp[i][j]: i..j 행렬 곱 최소 비용
    vector<vector<long long>> dp(n, vector<long long>(n, 0));

    // 구간 길이 2부터 증가
    for (int len = 2; len <= n; len++) {
        for (int i = 0; i + len - 1 < n; i++) {
            int j = i + len - 1;
            long long best = INF;

            for (int k = i; k < j; k++) {
                long long cost = dp[i][k] + dp[k + 1][j] + p[i] * p[k + 1] * p[j + 1];
                best = min(best, cost);
            }
            dp[i][j] = best;
        }
    }

    return (int)dp[0][n - 1];
}