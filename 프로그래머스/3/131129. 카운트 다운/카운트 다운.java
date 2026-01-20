import java.util.*;

class Solution {
    public int[] solution(int target) {
        final int INF = 1_000_000_000;

        // (score, singleOrBullCount)
        int[] scores = new int[61];
        int[] scnt = new int[61];
        int k = 0;

        for (int i = 1; i <= 20; i++) {
            scores[k] = i;     scnt[k] = 1; k++; // single
            scores[k] = i * 2; scnt[k] = 0; k++; // double
            scores[k] = i * 3; scnt[k] = 0; k++; // triple
        }
        scores[k] = 50; scnt[k] = 1; k++;       // bull

        int[] dpDarts = new int[target + 1];
        int[] dpSingles = new int[target + 1];

        Arrays.fill(dpDarts, INF);
        Arrays.fill(dpSingles, -INF);

        dpDarts[0] = 0;
        dpSingles[0] = 0;

        for (int t = 1; t <= target; t++) {
            for (int i = 0; i < k; i++) {
                int s = scores[i];
                if (t >= s && dpDarts[t - s] != INF) {
                    int candDarts = dpDarts[t - s] + 1;
                    int candSingles = dpSingles[t - s] + scnt[i];

                    if (candDarts < dpDarts[t] ||
                        (candDarts == dpDarts[t] && candSingles > dpSingles[t])) {
                        dpDarts[t] = candDarts;
                        dpSingles[t] = candSingles;
                    }
                }
            }
        }

        return new int[]{ dpDarts[target], dpSingles[target] };
    }
}