import math

def solution(n, k):
    # 1. n을 k진수 문자열로 변환
    def to_base_k(num, base):
        if num == 0:
            return "0"
        digits = []
        while num > 0:
            digits.append(str(num % base))
            num //= base
        return ''.join(reversed(digits))
    
    # 2. 소수 판별 함수
    def is_prime(x):
        if x < 2:
            return False
        if x == 2:
            return True
        if x % 2 == 0:
            return False
        limit = int(math.isqrt(x))
        for i in range(3, limit + 1, 2):
            if x % i == 0:
                return False
        return True

    base_str = to_base_k(n, k)
    # '0'을 기준으로 쪼개기
    parts = base_str.split('0')

    answer = 0
    for p in parts:
        if not p:          # 빈 문자열이면 패스 (연속된 0 사이 등)
            continue
        num = int(p)       # k진수 조각을 10진수로 해석하는 게 아님! 
                          # 이미 문자열은 "숫자열"이고, 문제에서 P는 10진수 소수 조건이므로 그냥 int()
        if is_prime(num):
            answer += 1

    return answer