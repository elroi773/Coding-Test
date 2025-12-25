import java.util.*;

class Solution {
    int n, half;
    int maxWin = -1;
    int[] bestPick;

    public int[] solution(int[][] dice) {
        n = dice.length;
        half = n / 2;
        bestPick = new int[half];

        boolean[] used = new boolean[n];
        int[] pick = new int[half];

        dfs(dice, 0, 0, pick, used);

        // 1-based index로 변환
        int[] answer = new int[half];
        for (int i = 0; i < half; i++) {
            answer[i] = bestPick[i] + 1;
        }
        return answer;
    }

    void dfs(int[][] dice, int idx, int cnt, int[] pick, boolean[] used) {
        if (cnt == half) {
            List<Integer> aList = new ArrayList<>();
            List<Integer> bList = new ArrayList<>();

            for (int i = 0; i < n; i++) {
                if (used[i]) aList.add(i);
                else bList.add(i);
            }

            Map<Integer, Integer> aDist = getDistribution(dice, aList);
            Map<Integer, Integer> bDist = getDistribution(dice, bList);

            int win = countWin(aDist, bDist);

            if (win > maxWin) {
                maxWin = win;
                for (int i = 0; i < half; i++) {
                    bestPick[i] = pick[i];
                }
            }
            return;
        }

        for (int i = idx; i < n; i++) {
            used[i] = true;
            pick[cnt] = i;
            dfs(dice, i + 1, cnt + 1, pick, used);
            used[i] = false;
        }
    }

    Map<Integer, Integer> getDistribution(int[][] dice, List<Integer> idxs) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        for (int idx : idxs) {
            Map<Integer, Integer> next = new HashMap<>();
            for (int sum : map.keySet()) {
                int cnt = map.get(sum);
                for (int face : dice[idx]) {
                    next.put(sum + face, next.getOrDefault(sum + face, 0) + cnt);
                }
            }
            map = next;
        }
        return map;
    }

    int countWin(Map<Integer, Integer> aDist, Map<Integer, Integer> bDist) {
        int maxB = 0;
        for (int b : bDist.keySet()) maxB = Math.max(maxB, b);

        int[] prefix = new int[maxB + 2];
        for (int b : bDist.keySet()) {
            prefix[b + 1] += bDist.get(b);
        }
        for (int i = 1; i < prefix.length; i++) {
            prefix[i] += prefix[i - 1];
        }

        int win = 0;
        for (int a : aDist.keySet()) {
            int cntA = aDist.get(a);
            if (a <= maxB) win += cntA * prefix[a];
            else win += cntA * prefix[maxB + 1];
        }
        return win;
    }
}