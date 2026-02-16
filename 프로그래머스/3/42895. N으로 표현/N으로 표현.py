def solution(N, number):
    if N == number:
        return 1

    dp = [set() for _ in range(9)]  # 0은 안 쓰고 1~8 사용

    for i in range(1, 9):
        # 이어붙인 숫자 (ex: 5, 55, 555 ...)
        concat = int(str(N) * i)
        dp[i].add(concat)

        # j + (i-j)로 쪼개서 사칙연산 조합
        for j in range(1, i):
            for a in dp[j]:
                for b in dp[i - j]:
                    dp[i].add(a + b)
                    dp[i].add(a - b)
                    dp[i].add(a * b)
                    if b != 0:
                        dp[i].add(a // b)  # 나머지 버림

        if number in dp[i]:
            return i

    return -1