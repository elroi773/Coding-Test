function solution(temperature, t1, t2, a, b, onboard) {
  const SHIFT = 10;          // -10 -> 0, 40 -> 50
  const MIN_T = -10, MAX_T = 40;
  const STATES = 51;
  const INF = 1e15;

  const lo = t1 + SHIFT;
  const hi = t2 + SHIFT;

  let dp = Array(STATES).fill(INF);
  dp[temperature + SHIFT] = 0; // 0분 실내온도 = 실외온도

  for (let i = 0; i < onboard.length - 1; i++) {
    // i분에 승객 탑승이면 i분 시점 온도는 [t1, t2]여야 함
    if (onboard[i] === 1) {
      for (let t = 0; t < STATES; t++) {
        if (t < lo || t > hi) dp[t] = INF;
      }
    }

    const ndp = Array(STATES).fill(INF);

    for (let idx = 0; idx < STATES; idx++) {
      const cost = dp[idx];
      if (cost >= INF) continue;

      const cur = idx - SHIFT;

      // 1) 에어컨 OFF: 실외온도 방향으로 1도 이동(또는 유지), 비용 0
      let nxt = cur;
      if (cur < temperature) nxt = cur + 1;
      else if (cur > temperature) nxt = cur - 1;
      ndp[nxt + SHIFT] = Math.min(ndp[nxt + SHIFT], cost);

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

    dp = ndp;
  }

  // 마지막 시점도 탑승이면 온도 제한 적용
  if (onboard[onboard.length - 1] === 1) {
    for (let t = 0; t < STATES; t++) {
      if (t < lo || t > hi) dp[t] = INF;
    }
  }

  return Math.min(...dp);
}