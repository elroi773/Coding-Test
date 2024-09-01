import java.util.Arrays;

class Solution {
    public int[] solution(int[][] score) {
        int n = score.length;
        double[] averages = new double[n];
        int[] ranks = new int[n];

        // 1. 평균 점수 계산
        for (int i = 0; i < n; i++) {
            averages[i] = (score[i][0] + score[i][1]) / 2.0;
        }

        // 2. 등수 계산
        for (int i = 0; i < n; i++) {
            int rank = 1;
            for (int j = 0; j < n; j++) {
                if (averages[j] > averages[i]) {
                    rank++;
                }
            }
            ranks[i] = rank;
        }

        return ranks;
    }
}
