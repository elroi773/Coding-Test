import math

def solution(n):
    x = math.isqrt(n)  # 정수 제곱근
    if x * x == n:
        return (x + 1) * (x + 1)
    else:
        return -1
