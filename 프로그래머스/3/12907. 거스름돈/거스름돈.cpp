#include <string>
#include <vector>

using namespace std;

int solution(int n, vector<int> money) {
    const int MOD = 1000000007;
    vector<int> dp(n + 1, 0);
    dp[0] = 1;

    for (int coin : money) {
        for (int i = coin; i <= n; i++) {
            dp[i] = (dp[i] + dp[i - coin]) % MOD;
        }
    }

    return dp[n];
}