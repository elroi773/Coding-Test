from collections import defaultdict

def solution(points, routes):
    x = len(routes)               # 로봇 수
    n = len(points)               # 포인트 수

    # 각 로봇의 상태 초기화
    rs = []        # 현재 r
    cs = []        # 현재 c
    seg_idx = []   # 현재 이동 중인 구간의 '도착 포인트' 인덱스 (route[seg_idx-1] -> route[seg_idx])
    active = []    # 아직 센터 안에 있는지 여부

    for route in routes:
        start_point_id = route[0] - 1   # 0-based
        r, c = points[start_point_id]
        rs.append(r)
        cs.append(c)
        seg_idx.append(1)               # 처음에는 route[0] -> route[1] 구간으로 이동 시작
        active.append(True)

    answer = 0

    while True:
        pos_counts = defaultdict(int)
        any_active = False

        # 1) 현재 시각의 위치로 충돌 카운트
        for i in range(x):
            if not active[i]:
                continue
            any_active = True
            pos_counts[(rs[i], cs[i])] += 1

        # 더 이상 센터 안에 로봇이 없으면 종료
        if not any_active:
            break

        # 좌표별로 2대 이상 모이면 위험 상황 1회
        for cnt in pos_counts.values():
            if cnt >= 2:
                answer += 1

        # 2) 다음 시각으로 이동
        for i in range(x):
            if not active[i]:
                continue

            route = routes[i]
            m = len(route)

            idx = seg_idx[i]  # 현재 도착 목표 포인트 인덱스 (1 <= idx <= m-1)

            # 현재 목표 포인트 좌표
            target_id = route[idx] - 1
            tr, tc = points[target_id]

            # 이미 목표 포인트에 서 있는 경우 (해당 구간 끝)
            if rs[i] == tr and cs[i] == tc:
                if idx == m - 1:
                    # 마지막 포인트에 도착 → 다음 시각부터 센터 밖
                    active[i] = False
                    continue
                else:
                    # 다음 구간으로 넘어감 (route[idx] -> route[idx+1])
                    idx += 1
                    seg_idx[i] = idx
                    target_id = route[idx] - 1
                    tr, tc = points[target_id]

            # 목표 좌표를 향해 r 우선, 한 칸 이동
            if rs[i] != tr:
                rs[i] += 1 if tr > rs[i] else -1
            elif cs[i] != tc:
                cs[i] += 1 if tc > cs[i] else -1
            # 둘 다 같으면 (이론상 여기 안 옴, 바로 위에서 처리)

    return answer