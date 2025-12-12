def solution(number, k):
    stack = []

    for num in number:
        # 현재 숫자가 스택 top보다 크면 pop하여 k 감소
        while stack and k > 0 and stack[-1] < num:
            stack.pop()
            k -= 1

        stack.append(num)

    # 아직 제거해야 할 k가 남았다면 뒤에서 제거
    if k > 0:
        stack = stack[:-k]

    return ''.join(stack)
