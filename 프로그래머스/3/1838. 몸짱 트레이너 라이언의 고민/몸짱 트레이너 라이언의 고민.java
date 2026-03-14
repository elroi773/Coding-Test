import java.util.*;

class Solution {

    static class IntListKey {
        List<Integer> list;

        IntListKey(List<Integer> list) {
            this.list = list;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof IntListKey)) return false;
            IntListKey other = (IntListKey) o;
            return list.equals(other.list);
        }

        @Override
        public int hashCode() {
            return list.hashCode();
        }
    }

    // 같은 행에서 선택된 열들이 모두 거리 d 이상인지 확인
    boolean validRowMask(int mask, int n, int d) {
        List<Integer> cols = new ArrayList<>();
        for (int c = 0; c < n; c++) {
            if ((mask & (1 << c)) != 0) cols.add(c);
        }

        for (int i = 0; i < cols.size(); i++) {
            for (int j = i + 1; j < cols.size(); j++) {
                if (cols.get(j) - cols.get(i) < d) return false;
            }
        }
        return true;
    }

    // 행 차이가 rowDiff일 때 prevMask와 curMask가 거리 d 조건 만족하는지 확인
    boolean compatible(int prevMask, int curMask, int n, int d, int rowDiff) {
        int need = d - rowDiff;
        if (need <= 0) return true;

        List<Integer> a = new ArrayList<>();
        List<Integer> b = new ArrayList<>();

        for (int c = 0; c < n; c++) {
            if ((prevMask & (1 << c)) != 0) a.add(c);
            if ((curMask & (1 << c)) != 0) b.add(c);
        }

        for (int x : a) {
            for (int y : b) {
                if (Math.abs(x - y) < need) return false;
            }
        }
        return true;
    }

    // 최소 거리 d 이상으로 k개 배치 가능한지 판정
    boolean canPlace(int n, int k, int d) {
        if (k <= 1) return true;
        if (d <= 1) return k <= n * n;

        List<Integer> masks = new ArrayList<>();
        List<Integer> bits = new ArrayList<>();
        int totalMask = 1 << n;

        for (int mask = 0; mask < totalMask; mask++) {
            if (validRowMask(mask, n, d)) {
                masks.add(mask);
                bits.add(Integer.bitCount(mask));
            }
        }

        int M = masks.size();
        int[] maskToIdx = new int[totalMask];
        Arrays.fill(maskToIdx, -1);
        for (int i = 0; i < M; i++) {
            maskToIdx[masks.get(i)] = i;
        }

        int historyLen = Math.min(n - 1, d - 1);

        boolean[][][] compatTable = new boolean[historyLen + 1][M][M];
        for (int diff = 1; diff <= historyLen; diff++) {
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < M; j++) {
                    compatTable[diff][i][j] = compatible(masks.get(i), masks.get(j), n, d, diff);
                }
            }
        }

        Map<IntListKey, Integer> dp = new HashMap<>();
        dp.put(new IntListKey(new ArrayList<>()), 0);

        for (int row = 0; row < n; row++) {
            Map<IntListKey, Integer> ndp = new HashMap<>();

            for (Map.Entry<IntListKey, Integer> entry : dp.entrySet()) {
                List<Integer> history = entry.getKey().list;
                int cnt = entry.getValue();

                for (int curIdx = 0; curIdx < M; curIdx++) {
                    int curMask = masks.get(curIdx);
                    boolean ok = true;

                    for (int t = 1; t <= history.size(); t++) {
                        int prevMask = history.get(history.size() - t);
                        int prevIdx = maskToIdx[prevMask];
                        if (!compatTable[t][prevIdx][curIdx]) {
                            ok = false;
                            break;
                        }
                    }

                    if (!ok) continue;

                    List<Integer> nextHistory = new ArrayList<>(history);
                    nextHistory.add(curMask);
                    if (nextHistory.size() > historyLen) {
                        nextHistory.remove(0);
                    }

                    int nextCnt = cnt + bits.get(curIdx);
                    IntListKey key = new IntListKey(nextHistory);

                    Integer prev = ndp.get(key);
                    if (prev == null || prev < nextCnt) {
                        ndp.put(key, nextCnt);
                    }
                }
            }

            dp = ndp;
        }

        int best = 0;
        for (int val : dp.values()) {
            best = Math.max(best, val);
        }
        return best >= k;
    }

    public int solution(int n, int m, int[][] timetable) {
        int[] diff = new int[1324];

        // 퇴실시간과 같은 입실시간도 겹치므로 end+1 에 -1
        for (int[] t : timetable) {
            int s = t[0];
            int e = t[1];
            diff[s] += 1;
            diff[e + 1] -= 1;
        }

        int cur = 0;
        int maxOverlap = 0;
        for (int time = 600; time <= 1321; time++) {
            cur += diff[time];
            maxOverlap = Math.max(maxOverlap, cur);
        }

        if (maxOverlap <= 1) return 0;

        for (int d = 2 * (n - 1); d >= 1; d--) {
            if (canPlace(n, maxOverlap, d)) {
                return d;
            }
        }

        return 0;
    }
}