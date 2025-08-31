def solution(s):
    sign = 1
    start = 0
    answer = 0

    if s[0] == '-':
        sign = -1
        start = 1
    elif s[0] == '+':
        start = 1

    for i in range(start, len(s)):
        answer = answer * 10 + (ord(s[i]) - ord('0'))

    return sign * answer
