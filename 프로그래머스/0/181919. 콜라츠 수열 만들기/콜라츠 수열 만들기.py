def solution(n):
    answer = []
    while n != 1:
        answer.append(n)
        if n % 2 == 0:
            n = n // 2
        else:
            n = 3 * n + 1
    answer.append(1)  # 마지막 1 추가
    return answer
