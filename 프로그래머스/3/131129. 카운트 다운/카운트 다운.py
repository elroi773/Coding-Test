def solution(target):
    INF = 10**9

    # (score, single_or_bull_count)
    throws_list = []
    for i in range(1, 21):
        throws_list.append((i, 1))      # single
        throws_list.append((i * 2, 0))  # double
        throws_list.append((i * 3, 0))  # triple
    throws_list.append((50, 1))         # bull

    dp_darts = [INF] * (target + 1)
    dp_singles = [-INF] * (target + 1)

    dp_darts[0] = 0
    dp_singles[0] = 0

    for t in range(1, target + 1):
        best_d = INF
        best_s = -INF
        for score, s_cnt in throws_list:
            if t >= score and dp_darts[t - score] != INF:
                cand_d = dp_darts[t - score] + 1
                cand_s = dp_singles[t - score] + s_cnt

                if cand_d < best_d or (cand_d == best_d and cand_s > best_s):
                    best_d = cand_d
                    best_s = cand_s

        dp_darts[t] = best_d
        dp_singles[t] = best_s

    return [dp_darts[target], dp_singles[target]]