def solution(n, s):
    if s < n:
        return [-1]
    
    base = s // n
    remain = s % n
    
    answer = [base] * n
    
    for i in range(n - remain, n):
        answer[i] += 1
    
    return answer