class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long needG = a;
        long needS = b;
        long needT = needG + needS;

        long lo = 0L;
        long hi = 4_000_000_000_000_000L; // 4e15, 충분히 큰 상한

        while (lo < hi) {
            long mid = (lo + hi) / 2;
            if (can(mid, needG, needS, needT, g, s, w, t)) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return lo;
    }

    private boolean can(long time, long needG, long needS, long needT,
                        int[] g, int[] s, int[] w, int[] t) {
        long sumG = 0L;
        long sumS = 0L;
        long sumAll = 0L;

        for (int i = 0; i < g.length; i++) {
            long gi = g[i];
            long si = s[i];
            long wi = w[i];
            long ti = t[i];

            long round = ti * 2L;
            long trips = time / round;
            if (time % round >= ti) trips++; // 마지막 편도 1회 가능

            long cap = trips * wi;

            sumG += Math.min(gi, cap);
            sumS += Math.min(si, cap);
            sumAll += Math.min(gi + si, cap);

            // 필요 이상 누적은 잘라서 오버플로/불필요 계산 방지
            if (sumG > needG) sumG = needG;
            if (sumS > needS) sumS = needS;
            if (sumAll > needT) sumAll = needT;
        }

        return sumG >= needG && sumS >= needS && sumAll >= needT;
    }
}