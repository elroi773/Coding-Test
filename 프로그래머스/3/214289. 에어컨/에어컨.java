import java.util.Arrays;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        final int SHIFT = 10;          // -10 -> 0, 40 -> 50
        final int MIN_T = -10, MAX_T = 40;
        final int STATES = 51;
        final int INF = 1_000_000_000;

        int lo = t1 + SHIFT;
        int hi = t2 + SHIFT;

        int[] dp = new int[STATES];
        int[] ndp = new int[STATES];
        Arrays.fill(dp, INF);
        dp[temperature + SHIFT] = 0;   // 0분 실내온도 = 실외온도

        for (int i = 0; i < onboard.length - 1; i++) {
            // i분에 승객 탑승이면 i분 시점 온도는 [t1, t2]여야 함
            if (onboard[i] == 1) {
                for (int t = 0; t < STATES; t++) {
                    if (t < lo || t > hi) dp[t] = INF;
                }
            }

            Arrays.fill(ndp, INF);

            for (int idx = 0; idx < STATES; idx++) {
                int cost = dp[idx];
                if (cost >= INF) continue;

                int cur = idx - SHIFT;

                // 1) 에어컨 OFF: 실외온도 방향으로 1도 이동(또는 유지), 비용 0
                int nxt = cur;
                if (cur < temperature) nxt = cur + 1;
                else if (cur > temperature) nxt = cur - 1;
                int nidx = nxt + SHIFT;
                ndp[nidx] = Math.min(ndp[nidx], cost);

                // 2) 에어컨 ON & 희망온도 = 현재온도: 유지, 비용 b
                ndp[idx] = Math.min(ndp[idx], cost + b);

                // 3) 에어컨 ON & 희망온도 > 현재온도: +1, 비용 a
                if (cur < MAX_T) {
                    ndp[idx + 1] = Math.min(ndp[idx + 1], cost + a);
                }

                // 4) 에어컨 ON & 희망온도 < 현재온도: -1, 비용 a
                if (cur > MIN_T) {
                    ndp[idx - 1] = Math.min(ndp[idx - 1], cost + a);
                }
            }

            int[] tmp = dp; dp = ndp; ndp = tmp;
        }

        // 마지막 시점도 탑승이면 온도 제한 적용
        if (onboard[onboard.length - 1] == 1) {
            for (int t = 0; t < STATES; t++) {
                if (t < lo || t > hi) dp[t] = INF;
            }
        }

        int ans = INF;
        for (int v : dp) ans = Math.min(ans, v);
        return ans;
    }
}