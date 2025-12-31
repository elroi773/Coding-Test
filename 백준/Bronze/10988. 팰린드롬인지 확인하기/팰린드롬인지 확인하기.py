s = input().strip()
l, r = 0, len(s) - 1

while l < r:
    if s[l] != s[r]:
        print(0)
        break
    l += 1
    r -= 1
else:
    print(1)