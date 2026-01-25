def solution(enroll, referral, seller, amount):
    # 이름 -> 인덱스 매핑
    idx = {name: i for i, name in enumerate(enroll)}
    # 추천인(부모) 인덱스: 없으면 -1(센터)
    parent = [-1] * len(enroll)
    for i, ref in enumerate(referral):
        parent[i] = -1 if ref == "-" else idx[ref]

    profit = [0] * len(enroll)

    # 각 판매 기록 처리
    for name, cnt in zip(seller, amount):
        cur = idx[name]
        money = cnt * 100

        while cur != -1 and money > 0:
            give = money // 10          # 10% (원단위 절사)
            keep = money - give
            profit[cur] += keep
            cur = parent[cur]
            money = give                # 위로 전달되는 금액

    return profit