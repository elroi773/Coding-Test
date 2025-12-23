def solution(n):
    MOD = 1234567

    a, b = 0, 1  # F(0), F(1)
    for _ in range(2, n + 1):
        a, b = b, (a + b) % MOD

    return b
