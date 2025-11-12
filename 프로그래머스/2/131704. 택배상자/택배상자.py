def solution(order):
    stack = []       # 보조 벨트
    current = 1      # 현재 컨테이너 벨트에서 꺼낼 상자 번호
    answer = 0       # 트럭에 실은 상자 개수

    for box in order:
        # 컨테이너 벨트에서 상자를 꺼내 보조 벨트에 쌓음
        while current <= len(order) and (not stack or stack[-1] != box):
            stack.append(current)
            current += 1
        
        # 스택의 맨 위가 현재 필요한 상자라면 꺼냄
        if stack and stack[-1] == box:
            stack.pop()
            answer += 1
        else:
            break  # 더 이상 실을 수 없음

    return answer
