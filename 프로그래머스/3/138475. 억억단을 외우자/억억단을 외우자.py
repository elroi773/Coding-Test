def solution(e, starts):
    # 1) 약수 개수
    cnt = [0] * (e + 1)
    for i in range(1, e + 1):
        for j in range(i, e + 1, i):
            cnt[j] += 1

    # 2) best[i]
    best = [0] * (e + 1)
    best_num = e
    best_cnt = cnt[e]
    best[e] = e

    for i in range(e - 1, 0, -1):
        if cnt[i] >= best_cnt:   # 동점이면 더 작은 i
            best_cnt = cnt[i]
            best_num = i
        best[i] = best_num

    # 3) 질의
    return [best[s] for s in starts]