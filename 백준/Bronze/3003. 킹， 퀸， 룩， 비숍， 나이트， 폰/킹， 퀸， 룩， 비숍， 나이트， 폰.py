pieces = [1, 1, 2, 2, 2, 8]
found = list(map(int, input().split()))

for i in range(6):
    print(pieces[i] - found[i], end=' ')