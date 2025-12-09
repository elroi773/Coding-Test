def solution(p):
    # 올바른 괄호 문자열인지 검사
    def is_correct(s):
        stack = []
        for c in s:
            if c == '(':
                stack.append(c)
            else:
                if not stack:
                    return False
                stack.pop()
        return True

    # p가 빈 문자열이면 그대로 반환
    if p == "":
        return ""

    # 1. u, v 분리 (u는 가장 앞에서부터 균형잡힌 괄호 문자열)
    balance = 0
    for i in range(len(p)):
        if p[i] == '(':
            balance += 1
        else:
            balance -= 1
        
        if balance == 0:   # 균형잡힌 지점 찾음
            u = p[:i+1]
            v = p[i+1:]
            break

    # 2. u가 올바른 문자열이면, v를 재귀 처리해서 붙임
    if is_correct(u):
        return u + solution(v)

    # 3. u가 올바르지 않으면 규칙에 따라 새 문자열 생성
    else:
        result = "("
        result += solution(v)
        result += ")"

        # u의 첫/마지막 제거 후 뒤집기
        flipped = ""
        for c in u[1:-1]:
            if c == '(':
                flipped += ')'
            else:
                flipped += '('

        result += flipped
        return result
