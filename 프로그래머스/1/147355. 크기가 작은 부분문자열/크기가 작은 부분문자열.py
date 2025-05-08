def solution(t, p):
    answer = 0
    len_p = len(p)
    int_p = int(p)
    
    for i in range(len(t) - len_p + 1):
        sub = t[i:i+len_p]
        if int(sub) <= int_p:
            answer += 1
            
    return answer
