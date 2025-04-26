def solution(my_string, alp):
    answer = ''
    for c in my_string:
        if c == alp:
            answer += c.upper()
        else:
            answer += c
    return answer
