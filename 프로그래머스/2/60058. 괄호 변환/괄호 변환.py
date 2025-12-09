def solution(p):
    return convert(p)

def convert(p):
    # 1. 빈 문자열 → 그대로 반환
    if p == "":
        return ""

    # 2. u, v 분리 (u는 균형잡힌 괄호 문자열)
    balance = 0
    for i in range(len(p)):
        if p[i] == '(':
            balance += 1
        else:
            balance -= 1
        
        if balance == 0:  # 균형잡힌 지점
            u = p[:i+1]
            v = p[i+1:]
            break

    # 3. u가 올바른 문자열인지 확인
    if is_correct(u):
        return u + convert(v)

    # 4. u가 올바르지 않으면 규칙대로 새 문자열 생성
    result = "("
    result += convert(v)
    result += ")"

    # 4-4. u의 앞/뒤 제거 후 괄호 방향을 뒤집기
    flipped = ""
    for c in u[1:-1]:
        flipped += '(' if c == ')' else ')'

    return result + flipped


# 올바른 괄호 문자열인지 확인하는 함수
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
