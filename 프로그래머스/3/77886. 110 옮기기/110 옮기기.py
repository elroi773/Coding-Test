def solution(s):
    answer = []

    for x in s:
        stack = []
        cnt110 = 0

        # 1) "110" 전부 제거(스택)
        for ch in x:
            stack.append(ch)
            if len(stack) >= 3 and stack[-3] == '1' and stack[-2] == '1' and stack[-1] == '0':
                stack.pop(); stack.pop(); stack.pop()
                cnt110 += 1

        # 2) 삽입 위치: 남은 문자열에서 마지막 '0' 뒤(없으면 0)
        insert_idx = 0
        for i in range(len(stack) - 1, -1, -1):
            if stack[i] == '0':
                insert_idx = i + 1
                break

        # 3) 결과 조립
        mid = "110" * cnt110
        res = ''.join(stack[:insert_idx]) + mid + ''.join(stack[insert_idx:])
        answer.append(res)

    return answer