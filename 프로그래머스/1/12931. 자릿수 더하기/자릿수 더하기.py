def solution(n):
    answer = 0
    while n > 0:
        answer += n % 10   # 마지막 자리 더하기
        n //= 10           # 마지막 자리 제거
    return answer

# 실행 예시
print(solution(123))  # 6
print(solution(987))  # 24
