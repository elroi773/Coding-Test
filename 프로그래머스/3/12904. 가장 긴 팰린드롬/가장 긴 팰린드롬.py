def solution(s):
    def expand(left, right):
        while left >= 0 and right < len(s) and s[left] == s[right]:
            left -= 1
            right += 1
        return right - left - 1

    answer = 1

    for i in range(len(s)):
        odd = expand(i, i)       # 홀수 길이
        even = expand(i, i + 1)  # 짝수 길이

        answer = max(answer, odd, even)

    return answer