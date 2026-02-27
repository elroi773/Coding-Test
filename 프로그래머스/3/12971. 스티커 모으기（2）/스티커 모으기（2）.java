import java.util.*;

class Solution {
    private int solveLinear(int[] s, int l, int r) { // inclusive [l..r]
        int prev2 = 0; // dp[i-2]
        int prev1 = 0; // dp[i-1]
        for (int i = l; i <= r; i++) {
            int cur = Math.max(prev1, prev2 + s[i]);
            prev2 = prev1;
            prev1 = cur;
        }
        return prev1;
    }

    public int solution(int[] sticker) {
        int n = sticker.length;
        if (n == 1) return sticker[0];
        if (n == 2) return Math.max(sticker[0], sticker[1]);

        // Case A: take first -> cannot take last => [0..n-2]
        int caseA = solveLinear(sticker, 0, n - 2);

        // Case B: skip first -> can take last => [1..n-1]
        int caseB = solveLinear(sticker, 1, n - 1);

        return Math.max(caseA, caseB);
    }
}