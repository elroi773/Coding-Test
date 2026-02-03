import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        int n = gems.length;

        // 전체 보석 종류 수
        Set<String> kindSet = new HashSet<>(Arrays.asList(gems));
        int need = kindSet.size();

        Map<String, Integer> count = new HashMap<>();
        int l = 0;

        int bestL = 0, bestR = n - 1;
        int bestLen = bestR - bestL;

        for (int r = 0; r < n; r++) {
            count.put(gems[r], count.getOrDefault(gems[r], 0) + 1);

            // 모든 종류를 포함하면 왼쪽을 최대한 줄이기
            while (count.size() == need && l <= r) {
                int len = r - l;
                if (len < bestLen || (len == bestLen && l < bestL)) {
                    bestLen = len;
                    bestL = l;
                    bestR = r;
                }

                String leftGem = gems[l];
                int c = count.get(leftGem) - 1;
                if (c == 0) count.remove(leftGem);
                else count.put(leftGem, c);

                l++;
            }
        }

        // 진열대 번호는 1부터
        return new int[] { bestL + 1, bestR + 1 };
    }
}