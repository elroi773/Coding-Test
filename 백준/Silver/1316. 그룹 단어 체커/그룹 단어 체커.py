def is_group_word(word):
    seen = set()
    prev = ''

    for ch in word:
        if ch != prev:
            if ch in seen:
                return False
            seen.add(ch)
        prev = ch

    return True


n = int(input())
count = 0

for _ in range(n):
    word = input().strip()
    if is_group_word(word):
        count += 1

print(count)