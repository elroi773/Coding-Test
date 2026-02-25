def solution(A, B):
    a = sorted(A)
    b = sorted(B)

    i = 0  # A 포인터
    j = 0  # B 포인터
    answer = 0

    # B에서 현재 A를 이길 수 있는 가장 작은 수를 매칭
    while i < len(a) and j < len(b):
        if b[j] > a[i]:
            answer += 1
            i += 1
            j += 1
        else:
            j += 1

    return answer