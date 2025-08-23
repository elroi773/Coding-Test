def solution(n):
    for x in range(1, n):
        if n % x == 1:
            return x

# 테스트
print(solution(10))  # 3
print(solution(12))  # 11
