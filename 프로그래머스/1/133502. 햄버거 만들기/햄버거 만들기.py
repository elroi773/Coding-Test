def solution(ingredient):
    answer = 0
    stack = []

    for i in ingredient:
        stack.append(i)

        if len(stack) >= 4:
            if stack[-4:] == [1, 2, 3, 1]:
                # 햄버거 완성 → 마지막 4개 제거
                del stack[-4:]
                answer += 1

    return answer
