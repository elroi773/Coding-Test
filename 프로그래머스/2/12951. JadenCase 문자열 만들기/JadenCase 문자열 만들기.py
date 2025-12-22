def solution(s):
    answer = []
    is_start = True  # 단어 시작 여부

    for c in s:
        if c == ' ':
            answer.append(c)
            is_start = True
        else:
            if is_start:
                answer.append(c.upper())
                is_start = False
            else:
                answer.append(c.lower())

    return ''.join(answer)
