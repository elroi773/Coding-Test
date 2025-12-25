from itertools import combinations
from collections import defaultdict

def solution(dice):
    n = len(dice)
    half = n // 2
    idx = list(range(n))
    
    def get_sum_distribution(dice_idxs):
        dist = defaultdict(int)
        dist[0] = 1
        
        for i in dice_idxs:
            new_dist = defaultdict(int)
            for s, cnt in dist.items():
                for v in dice[i]:
                    new_dist[s + v] += cnt
            dist = new_dist
        return dist

    max_win = -1
    answer = []

    for a_idxs in combinations(idx, half):
        b_idxs = [i for i in idx if i not in a_idxs]
        
        A_dist = get_sum_distribution(a_idxs)
        B_dist = get_sum_distribution(b_idxs)

        # B의 누적 분포
        max_b = max(B_dist.keys())
        prefix = [0] * (max_b + 2)
        for s, cnt in B_dist.items():
            prefix[s + 1] += cnt
        for i in range(1, len(prefix)):
            prefix[i] += prefix[i - 1]

        win = 0
        for a_sum, a_cnt in A_dist.items():
            if a_sum <= max_b:
                win += a_cnt * prefix[a_sum]
            else:
                win += a_cnt * prefix[-1]

        if win > max_win:
            max_win = win
            answer = [i + 1 for i in a_idxs]

    return answer
