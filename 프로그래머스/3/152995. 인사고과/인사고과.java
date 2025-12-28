import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int wanhoA = scores[0][0];
        int wanhoB = scores[0][1];
        int wanhoSum = wanhoA + wanhoB;

        // 근무태도 내림차순, 동료평가 오름차순
        Arrays.sort(scores, (o1, o2) -> {
            if (o1[0] != o2[0]) return o2[0] - o1[0];
            return o1[1] - o2[1];
        });

        int maxB = 0;
        int rank = 1;

        for (int[] s : scores) {
            int a = s[0];
            int b = s[1];

            // 지배당한 경우
            if (b < maxB) {
                if (a == wanhoA && b == wanhoB) {
                    return -1;
                }
                continue;
            }

            maxB = b;

            if (a + b > wanhoSum) {
                rank++;
            }
        }

        return rank;
    }
}
