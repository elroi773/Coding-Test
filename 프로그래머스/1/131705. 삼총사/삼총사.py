def solution(number):
    answer = 0
    n = len(number)
    
    for i in range(n - 2):
        for j in range(i + 1, n - 1):
            for k in range(j + 1, n):
                if number[i] + number[j] + number[k] == 0:
                    answer += 1
    return answer

# 테스트
print(solution([-2, 3, 0, 2, -5]))        # 2
print(solution([-3, -2, -1, 0, 1, 2, 3])) # 5
print(solution([-1, 1, -1, 1]))           # 0
