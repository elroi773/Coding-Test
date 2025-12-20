def solution(s):
    stack = []
    
    for c in s:
        if stack and stack[-1] == c:
            stack.pop()      # 짝 제거
        else:
            stack.append(c)  # 스택에 추가
    
    return 1 if not stack else 0
