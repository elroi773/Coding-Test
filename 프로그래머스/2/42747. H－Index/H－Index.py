def solution(citations):
    citations.sort(reverse=True)  # 내림차순 정렬

    for i, c in enumerate(citations):
        if c < i + 1:  # h 조건을 만족하지 못하는 순간 break
            return i

    return len(citations)
