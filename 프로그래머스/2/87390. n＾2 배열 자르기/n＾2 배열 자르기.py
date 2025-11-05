def solution(n, left, right):
    answer = []
    for i in range(left, right + 1):
        row = i // n
        col = i % n
        answer.append(max(row, col) + 1)
    return answer
