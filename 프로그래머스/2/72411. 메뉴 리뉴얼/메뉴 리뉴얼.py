from itertools import combinations
from collections import Counter

def solution(orders, course):
    answer = []

    # 각 주문을 알파벳 순으로 정렬해 두기
    sorted_orders = [''.join(sorted(o)) for o in orders]

    for c in course:
        comb_list = []

        # 각 주문에서 길이 c짜리 조합을 전부 뽑아서 모으기
        for order in sorted_orders:
            if len(order) < c:
                continue
            comb_list += combinations(order, c)

        # 해당 길이 c에 대해 조합이 하나도 없으면 스킵
        if not comb_list:
            continue

        counter = Counter(comb_list)

        # 최소 2명 이상이 주문한 조합만 고려
        max_count = 0
        for cnt in counter.values():
            if cnt >= 2:
                max_count = max(max_count, cnt)

        if max_count < 2:
            continue

        # 최대 빈도수를 가진 조합들만 정답 후보에 추가
        for comb, cnt in counter.items():
            if cnt == max_count:
                answer.append(''.join(comb))

    # 사전 순 정렬 후 반환
    answer.sort()
    return answer