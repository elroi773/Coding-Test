from functools import reduce

def solution(num_list):
    if len(num_list) >= 11:
        return sum(num_list)  # 합 구하기
    else:
        return reduce(lambda x, y: x * y, num_list)  # 곱 구하기
