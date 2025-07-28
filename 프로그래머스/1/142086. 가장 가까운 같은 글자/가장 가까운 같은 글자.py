def solution(s):
    answer = []
    last_index = {}  # 문자의 마지막 등장 위치를 저장하는 딕셔너리

    for i, c in enumerate(s):
        if c in last_index:
            answer.append(i - last_index[c])
        else:
            answer.append(-1)
        last_index[c] = i  # 현재 위치로 갱신

    return answer
