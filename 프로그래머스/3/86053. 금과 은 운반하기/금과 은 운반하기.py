def solution(a, b, g, s, w, t):
    need_g = a
    need_s = b
    need_total = a + b

    def can(time):
        gold = 0
        silver = 0
        total = 0

        for gi, si, wi, ti in zip(g, s, w, t):
            round_trip = ti * 2
            trips = time // round_trip
            if time % round_trip >= ti:
                trips += 1  # 마지막 편도 1회 가능

            cap = trips * wi

            gold += min(gi, cap)
            silver += min(si, cap)
            total += min(gi + si, cap)

            # 필요 이상 누적은 잘라서 계산 안정/최적화
            if gold > need_g: gold = need_g
            if silver > need_s: silver = need_s
            if total > need_total: total = need_total

        return gold >= need_g and silver >= need_s and total >= need_total

    lo, hi = 0, 4_000_000_000_000_000  # 4e15

    while lo < hi:
        mid = (lo + hi) // 2
        if can(mid):
            hi = mid
        else:
            lo = mid + 1

    return lo