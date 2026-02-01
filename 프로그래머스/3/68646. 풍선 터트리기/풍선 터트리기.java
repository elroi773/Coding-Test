import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n <= 1) return n;

        // rightMin[i] = min(a[i+1..n-1]), 없으면 INF
        int[] rightMin = new int[n];
        int INF = Integer.MAX_VALUE;

        int cur = INF;
        for (int i = n - 1; i >= 0; i--) {
            rightMin[i] = cur;
            if (a[i] < cur) cur = a[i];
        }

        int answer = 0;
        int leftMin = INF;
        for (int i = 0; i < n; i++) {
            if (a[i] <= leftMin || a[i] <= rightMin[i]) {
                answer++;
            }
            if (a[i] < leftMin) leftMin = a[i];
        }

        return answer;
    }
}