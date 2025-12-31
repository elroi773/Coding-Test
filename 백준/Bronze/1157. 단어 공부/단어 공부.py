import sys

s = sys.stdin.readline().strip()

cnt = [0] * 26
for ch in s:
    idx = ord(ch.upper()) - ord('A')
    cnt[idx] += 1

mx = max(cnt)
if cnt.count(mx) > 1:
    print('?')
else:
    print(chr(cnt.index(mx) + ord('A')))