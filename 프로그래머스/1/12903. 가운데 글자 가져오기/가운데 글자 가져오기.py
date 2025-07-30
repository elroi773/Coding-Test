def solution(s):
    length = len(s)
    mid = length // 2

    if length % 2 == 0:
        return s[mid - 1:mid + 1]  # 짝수: 가운데 두 글자
    else:
        return s[mid]  # 홀수: 가운데 한 글자
