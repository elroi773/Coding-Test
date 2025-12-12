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

    # 1. 조합 가능한 모든 숫자 만들기
    for i in range(1, len(numbers) + 1):
        for p in permutations(numbers, i):
            nums.add(int("".join(p)))

    # 2. 소수 개수 세기
    count = 0
    for n in nums:
        if is_prime(n):
            count += 1

    return count
