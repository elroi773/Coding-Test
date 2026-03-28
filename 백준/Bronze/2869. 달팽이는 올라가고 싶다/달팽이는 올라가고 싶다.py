A, B, V = map(int, input().split())

day = (V - A + (A - B) - 1) // (A - B) + 1
print(day)