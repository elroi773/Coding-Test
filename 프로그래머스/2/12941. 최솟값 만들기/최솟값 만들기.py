def solution(A, B):
    answer = 0

    # A는 오름차순, B는 내림차순 정렬
    A.sort()
    B.sort(reverse=True)

    # 같은 인덱스끼리 곱해서 누적
    for i in range(len(A)):
        answer += A[i] * B[i]

    return answer
