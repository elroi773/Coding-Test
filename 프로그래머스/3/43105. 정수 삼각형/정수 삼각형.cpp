#include <vector>
#include <algorithm>
using namespace std;

int solution(vector<vector<int>> triangle) {
    int n = (int)triangle.size();

    // dp[i][j] = i행 j열까지 왔을 때의 최대 합
    vector<vector<int>> dp = triangle;

    for (int i = 1; i < n; i++) {
        for (int j = 0; j <= i; j++) {
            int fromLeft = (j > 0) ? dp[i-1][j-1] : -1;      // 왼쪽 위에서 내려오는 경우
            int fromRight = (j < i) ? dp[i-1][j] : -1;       // 오른쪽 위에서 내려오는 경우
            dp[i][j] = triangle[i][j] + max(fromLeft, fromRight);
        }
    }

    return *max_element(dp[n-1].begin(), dp[n-1].end());
}