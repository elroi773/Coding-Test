def solution(gems):
    need = len(set(gems))     # 포함해야 하는 보석 종류 수
    counter = {}
    l = 0

    best_l, best_r = 0, len(gems) - 1
    best_len = best_r - best_l

    for r, g in enumerate(gems):
        counter[g] = counter.get(g, 0) + 1

        # 모든 종류를 포함하면 왼쪽을 가능한 한 줄이기
        while len(counter) == need and l <= r:
            cur_len = r - l
            if cur_len < best_len:
                best_len = cur_len
                best_l, best_r = l, r

            left_g = gems[l]
            counter[left_g] -= 1
            if counter[left_g] == 0:
                del counter[left_g]
            l += 1

    # 진열대 번호는 1부터 시작
    return [best_l + 1, best_r + 1]