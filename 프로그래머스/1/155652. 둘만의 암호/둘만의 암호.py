def solution(s, skip, index):
    alphabet = [chr(i) for i in range(ord('a'), ord('z') + 1)]
    valid = [ch for ch in alphabet if ch not in skip]

    answer = ''
    for ch in s:
        i = valid.index(ch)
        new_ch = valid[(i + index) % len(valid)]
        answer += new_ch

    return answer
