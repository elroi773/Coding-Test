def solution(sequence, k):
    n = len(sequence)
    start, end = 0, 0
    current_sum = sequence[0]
    answer = [0, n-1]  # 초기값: 최대 길이로 설정
    min_len = n  # 최소 길이

    while start < n and end < n:
        if current_sum < k:
            end += 1
            if end < n:
                current_sum += sequence[end]
        elif current_sum > k:
            current_sum -= sequence[start]
            start += 1
        else:  # current_sum == k
            if (end - start) < min_len:
                min_len = end - start
                answer = [start, end]
            current_sum -= sequence[start]
            start += 1

    return answer
