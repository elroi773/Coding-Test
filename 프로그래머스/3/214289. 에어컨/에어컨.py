def solution(temperature, t1, t2, a, b, onboard):
    INF = 10**15
    SHIFT = 10  # -10 -> 0, 40 -> 50
    MOD_TEMP_MIN, MOD_TEMP_MAX = -10, 40

    L = len(onboard)
    lo, hi = t1 + SHIFT, t2 + SHIFT

    # dp[temp] = i분 시점의 실내온도가 temp일 때 최소 소비전력
    dp = [INF] * 51
    dp[temperature + SHIFT] = 0  # 0분 실내온도는 항상 실외온도

    for i in range(L - 1):
        # i분에 승객 탑승이면, i분 시점 온도는 [t1, t2]여야 함
        if onboard[i] == 1:
            for t in range(51):
                if t < lo or t > hi:
                    dp[t] = INF

        ndp = [INF] * 51
        for idx, cost in enumerate(dp):
            if cost >= INF:
                continue
            cur = idx - SHIFT

            # 1) 에어컨 OFF: 실외온도 방향으로 1도 이동(또는 유지), 비용 0
            nxt = cur
            if cur < temperature:
                nxt = cur + 1
            elif cur > temperature:
                nxt = cur - 1
            ndp[nxt + SHIFT] = min(ndp[nxt + SHIFT], cost)

            # 2) 에어컨 ON & 희망온도 = 현재온도: 유지, 비용 b
            ndp[idx] = min(ndp[idx], cost + b)

            # 3) 에어컨 ON & 희망온도 > 현재온도: +1, 비용 a
            if cur < MOD_TEMP_MAX:
                ndp[idx + 1] = min(ndp[idx + 1], cost + a)

            # 4) 에어컨 ON & 희망온도 < 현재온도: -1, 비용 a
            if cur > MOD_TEMP_MIN:
                ndp[idx - 1] = min(ndp[idx - 1], cost + a)

        dp = ndp

    # 마지막 시점(L-1분)도 탑승이면 온도 제한 적용
    if onboard[L - 1] == 1:
        for t in range(51):
            if t < lo or t > hi:
                dp[t] = INF

    return min(dp)