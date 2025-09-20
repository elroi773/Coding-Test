def solution(id_list, report, k):
    # 1. 중복 제거
    report = set(report)

    # 2. 신고 관계 저장
    reported_by = {user: [] for user in id_list}
    reported_count = {user: 0 for user in id_list}

    for r in report:
        user, target = r.split()
        reported_by[target].append(user)
        reported_count[target] += 1

    # 3. 정지된 유저 찾기
    banned = [user for user, cnt in reported_count.items() if cnt >= k]

    # 4. 메일 횟수 계산
    id_index = {user: i for i, user in enumerate(id_list)}
    answer = [0] * len(id_list)

    for b in banned:
        for reporter in reported_by[b]:
            answer[id_index[reporter]] += 1

    return answer
