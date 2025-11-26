def solution(cap, n, deliveries, pickups):
    answer = 0
    d_idx = n - 1  # 배달이 남은 마지막 집
    p_idx = n - 1  # 수거가 남은 마지막 집

    while d_idx >= 0 or p_idx >= 0:
        # 남은 배달 마지막 집 찾기
        while d_idx >= 0 and deliveries[d_idx] == 0:
            d_idx -= 1
        # 남은 수거 마지막 집 찾기
        while p_idx >= 0 and pickups[p_idx] == 0:
            p_idx -= 1

        if d_idx < 0 and p_idx < 0:
            break

        # 이번 회차 최대 거리
        distance = max(d_idx, p_idx) + 1
        answer += distance * 2  # 왕복 거리

        # 배달 처리
        load = cap
        i = d_idx
        while i >= 0 and load > 0:
            if deliveries[i] <= load:
                load -= deliveries[i]
                deliveries[i] = 0
                d_idx = i - 1
            else:
                deliveries[i] -= load
                load = 0
            i -= 1

        # 수거 처리
        load = cap
        i = p_idx
        while i >= 0 and load > 0:
            if pickups[i] <= load:
                load -= pickups[i]
                pickups[i] = 0
                p_idx = i - 1
            else:
                pickups[i] -= load
                load = 0
            i -= 1

    return answer
