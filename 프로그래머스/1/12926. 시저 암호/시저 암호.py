def solution(s, n):
    answer = ''
    for ch in s:
        if ch == ' ':
            answer += ' '   # 공백은 그대로
        elif 'A' <= ch <= 'Z':
            answer += chr((ord(ch) - ord('A') + n) % 26 + ord('A'))
        elif 'a' <= ch <= 'z':
            answer += chr((ord(ch) - ord('a') + n) % 26 + ord('a'))
    return answer
