def solution(targets):
    # 끝나는 지점(e) 기준으로 정렬
    targets.sort(key=lambda x: x[1])

    answer = 0
    last = -1  # 마지막 요격 미사일의 x 좌표를 나타내는 값 (논리적으로는 e 바로 앞)

    for s, e in targets:
        # 현재 구간의 시작이 last 이상이면, 기존 미사일로는 커버 불가
        if s >= last:
            answer += 1   # 새 미사일 발사
            last = e      # 이 구간의 끝 지점 바로 앞에 쏜 것으로 간주

    return answer