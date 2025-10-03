def solution(picks, minerals):
    fatigue = {
        "diamond": [1, 5, 25],
        "iron": [1, 1, 5],
        "stone": [1, 1, 1]
    }

    # 피로도 계산용 (곡괭이 종류: 0=다이아, 1=철, 2=돌)
    def calc(group, pick_type):
        f = 0
        for m in group:
            if m == "diamond":
                f += [1, 5, 25][pick_type]
            elif m == "iron":
                f += [1, 1, 5][pick_type]
            else:  # stone
                f += [1, 1, 1][pick_type]
        return f

    # 1) minerals를 5개 단위로 그룹화
    groups = []
    for i in range(0, len(minerals), 5):
        groups.append(minerals[i:i+5])

    # 2) 쓸 수 있는 곡괭이 수만큼만 그룹 고려
    total_picks = sum(picks)
    groups = groups[:total_picks]

    # 3) 각 그룹을 '돌 곡괭이로 캤을 때'의 비용 기준으로 정렬 (힘든 그룹 먼저)
    groups.sort(key=lambda g: -calc(g, 2))

    # 4) 곡괭이를 좋은 순서대로 배정
    answer = 0
    pick_list = []
    for i, num in enumerate(picks):
        pick_list.extend([i] * num)  # 0=dia, 1=iron, 2=stone
    pick_list.sort()  # 좋은 곡괭이부터 쓰게 정렬

    for g, p in zip(groups, pick_list):
        answer += calc(g, p)

    return answer
