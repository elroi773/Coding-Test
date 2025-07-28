def solution(s):
    answer = 0
    same = 0
    diff = 0
    first = ''

    for c in s:
        if same == 0:
            first = c
            same = 1
            diff = 0
        else:
            if c == first:
                same += 1
            else:
                diff += 1

        if same == diff:
            answer += 1
            same = 0
            diff = 0

    if same != 0 or diff != 0:
        answer += 1

    return answer
