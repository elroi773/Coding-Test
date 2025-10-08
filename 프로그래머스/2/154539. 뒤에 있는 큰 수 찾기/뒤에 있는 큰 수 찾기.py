def solution(numbers):
    n = len(numbers)
    answer = [-1] * n  # 초기값 -1
    stack = []  # 인덱스를 저장할 스택

    for i in range(n):
        # 현재 값이 스택 최상단 인덱스의 값보다 크면 뒷 큰수로 채움
        while stack and numbers[i] > numbers[stack[-1]]:
            idx = stack.pop()
            answer[idx] = numbers[i]
        stack.append(i)  # 현재 인덱스를 스택에 추가

    return answer