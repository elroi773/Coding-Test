from itertools import permutations
import math

def is_prime(n):
    if n < 2:
        return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0:
            return False
    return True

def solution(numbers):
    nums = set()

    # 1. 가능한 모든 숫자 조합 생성
    for i in range(1, len(numbers) + 1):
        for p in permutations(numbers, i):
            nums.add(int("".join(p)))  # 문자열 → int로 변환 후 set에 넣기 (중복 제거)

    # 2. 소수 개수 세기
    return sum(1 for n in nums if is_prime(n))
