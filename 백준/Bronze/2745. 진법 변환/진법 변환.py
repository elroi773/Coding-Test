n, b = input().split()
b = int(b)

result = 0

for ch in n:
    if '0' <= ch <= '9':
        value = ord(ch) - ord('0')
    else:
        value = ord(ch) - ord('A') + 10
    
    result = result * b + value

print(result)