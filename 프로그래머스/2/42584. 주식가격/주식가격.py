def solution(prices):
    n = len(prices)
    answer = [0] * n
    stack = []  # 인덱스 저장

    for i in range(n):
        # 가격이 떨어진 경우
        while stack and prices[i] < prices[stack[-1]]:
            j = stack.pop()
            answer[j] = i - j
        stack.append(i)

    # 끝까지 가격이 안 떨어진 경우
    while stack:
        j = stack.pop()
        answer[j] = n - 1 - j

    return answer
