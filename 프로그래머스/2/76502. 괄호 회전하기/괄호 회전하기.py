def solution(s):
    # 올바른 괄호 문자열인지 체크하는 함수
    def is_valid(string):
        stack = []
        pair = {')':'(', ']':'[', '}':'{'}
        
        for ch in string:
            if ch in '([{':
                stack.append(ch)
            else:
                # 닫는 괄호인데 스택이 비었거나, 짝이 안 맞으면 false
                if not stack or stack[-1] != pair[ch]:
                    return False
                stack.pop()
        return len(stack) == 0

    answer = 0
    for x in range(len(s)):
        rotated = s[x:] + s[:x]   # 왼쪽으로 x칸 회전
        if is_valid(rotated):
            answer += 1

    return answer
