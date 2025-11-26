def solution(cap, n, deliveries, pickups):
    answer = 0
    # 마지막으로 배달/수거가 필요한 집의 인덱스를 찾기
    d_idx = n - 1
    p_idx = n - 1

    while d_idx >= 0 or p_idx >= 0:
        # 아직 배달할 것이 남은 마지막 집 찾기
        while d_idx >= 0 and deliveries[d_idx] == 0:
            d_idx -= 1
        # 아직 수거할 것이 남은 마지막 집 찾기
        while p_idx >= 0 and pickups[p_idx] == 0:
            p_idx -= 1

        # 더 이상 배달/수거가 없으면 종료
        if d_idx < 0 and p_idx < 0:
            break

        # 이번 회차에서 가장 멀리 가야 하는 거리
        distance = max(d_idx, p_idx) + 1
        answer += distance * 2  # 왕복 거리

        # 배달 처리
        load = cap
        i = d_idx
        while i >= 0 and load > 0:
            if deliveries[i] <= load:
                load -= deliveries[i]
                deliveries[i] = 0
                i -= 1
            else:
                deliveries[i] -= load
                load = 0
        # 수거 처리
        load = cap
        i = p_idx
        while i >= 0 and load > 0:
            if pickups[i] <= load:
                load -= pickups[i]
                pickups[i] = 0
                i -= 1
            else:
                pickups[i] -= load
                load = 0

    return answer
