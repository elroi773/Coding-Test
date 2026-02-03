def solution(gems):
    n = len(gems)
    need = len(set(gems))  # 포함해야 하는 보석 종류 수

    counter = {}
    l = 0
    best_l, best_r = 0, n - 1  # 최악의 경우 전체 구간

    for r in range(n):
        g = gems[r]
        counter[g] = counter.get(g, 0) + 1

        # 모든 종류를 포함하면 왼쪽을 줄일 수 있을 때까지 줄이기
        while len(counter) == need and l <= r:
            # 현재 구간이 더 짧으면 갱신
            if (r - l) < (best_r - best_l):
                best_l, best_r = l, r

            left_gem = gems[l]
            counter[left_gem] -= 1
            if counter[left_gem] == 0:
                del counter[left_gem]
            l += 1

    # 진열대 번호는 1부터 시작
    return [best_l + 1, best_r + 1]