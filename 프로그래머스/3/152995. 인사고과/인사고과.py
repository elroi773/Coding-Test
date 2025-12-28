def solution(scores):
    wanho_a, wanho_b = scores[0]
    wanho_sum = wanho_a + wanho_b

    # 근무태도 내림차순, 동료평가 오름차순
    scores.sort(key=lambda x: (-x[0], x[1]))

    max_b = 0
    valid_sums = []

    for a, b in scores:
        if b < max_b:
            # 지배당함
            if a == wanho_a and b == wanho_b:
                return -1
            continue
        max_b = b
        valid_sums.append(a + b)

    # 석차 계산
    rank = 1
    for s in sorted(valid_sums, reverse=True):
        if s > wanho_sum:
            rank += 1

    return rank
