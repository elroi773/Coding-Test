t = int(input())

for _ in range(t):
    c = int(input())

    q = c // 25
    c %= 25

    d = c // 10
    c %= 10

    n = c // 5
    c %= 5

    p = c

    print(q, d, n, p)