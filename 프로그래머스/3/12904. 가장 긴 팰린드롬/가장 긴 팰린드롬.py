def solution(s):
    n = len(s)
    
    if n == 1:
        return 1
    
    answer = 1

    def expand(left, right):
        while left >= 0 and right < n and s[left] == s[right]:
            left -= 1
            right += 1
        return right - left - 1

    for i in range(n):
        # 홀수 길이 팰린드롬
        answer = max(answer, expand(i, i))
        
        # 짝수 길이 팰린드롬
        answer = max(answer, expand(i, i + 1))

    return answer