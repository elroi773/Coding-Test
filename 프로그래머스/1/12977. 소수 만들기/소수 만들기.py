from itertools import combinations
import math

def solution(nums):
    answer = 0

    # 소수 판별 함수
    def is_prime(n):
        if n < 2:
            return False
        for i in range(2, int(math.sqrt(n)) + 1):
            if n % i == 0:
                return False
        return True

    # 3개 조합 탐색
    for comb in combinations(nums, 3):
        if is_prime(sum(comb)):
            answer += 1

    return answer
