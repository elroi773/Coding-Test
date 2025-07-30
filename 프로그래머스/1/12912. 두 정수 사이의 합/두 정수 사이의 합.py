def solution(a, b):
    start = min(a, b)
    end = max(a, b)
    total = 0
    for i in range(start, end + 1):
        total += i
    return total
