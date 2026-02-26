def solution(n, stations, w):
    answer = 0
    cover = 2 * w + 1

    position = 1  # 아직 커버 안 된 시작 아파트(1-based)

    for s in stations:
        left = s - w
        right = s + w

        # position ~ (left-1) 구간이 비어있으면 채우기
        if position < left:
            gap = left - position
            answer += (gap + cover - 1) // cover  # ceil(gap/cover)

        # 이 기지국이 커버하는 구간 다음으로 이동
        position = max(position, right + 1)

        if position > n:
            break

    # 마지막 기지국 이후 남은 구간 처리
    if position <= n:
        gap = n - position + 1
        answer += (gap + cover - 1) // cover

    return answer