def solution(n, times):
    times.sort()

    left, right = 1, times[-1] * n
    answer = right

    while left <= right:
        mid = (left + right) // 2

        processed = 0
        for t in times:
            processed += mid // t
            if processed >= n:
                break

        if processed >= n:
            answer = mid
            right = mid - 1
        else:
            left = mid + 1

    return answer