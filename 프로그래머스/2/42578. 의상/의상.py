def solution(clothes):
    clothes_dict = {}

    # 종류별 개수 세기
    for name, kind in clothes:
        clothes_dict[kind] = clothes_dict.get(kind, 0) + 1

    answer = 1
    # (개수 + 1)씩 곱하기
    for count in clothes_dict.values():
        answer *= (count + 1)

    # 아무것도 안 입은 경우 제외
    return answer - 1
