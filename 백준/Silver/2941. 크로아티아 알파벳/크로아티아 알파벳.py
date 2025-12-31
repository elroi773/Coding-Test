s = input().strip()
patterns = ["dz=", "c=", "c-", "d-", "lj", "nj", "s=", "z="]

for p in patterns:
    s = s.replace(p, "*")  # 한 글자로 치환
print(len(s))