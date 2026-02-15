import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int n = triangle.length;
        int[][] dp = new int[n][];
        
        for (int i = 0; i < n; i++) {
            dp[i] = new int[i + 1];
        }

        dp[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= i; j++) {
                int fromLeft = (j > 0) ? dp[i - 1][j - 1] : -1;  // 왼쪽 위
                int fromRight = (j < i) ? dp[i - 1][j] : -1;     // 오른쪽 위
                dp[i][j] = triangle[i][j] + Math.max(fromLeft, fromRight);
            }
        }

        int answer = 0;
        for (int v : dp[n - 1]) answer = Math.max(answer, v);
        return answer;
    }
}