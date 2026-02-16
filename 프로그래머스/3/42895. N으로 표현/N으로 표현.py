def solution(N, number):
    if N == number:
        return 1

    dp = [set() for _ in range(9)]  # dp[i] = N을 i번 사용해서 만들 수 있는 값들 (1~8)

    for i in range(1, 9):
        # 이어붙인 수: N, NN, NNN...
        dp[i].add(int(str(N) * i))

        # j + (i-j)로 분할해서 사칙연산 조합
        for j in range(1, i):
            for a in dp[j]:
                for b in dp[i - j]:
                    dp[i].add(a + b)
                    dp[i].add(a - b)
                    dp[i].add(a * b)
                    if b != 0:
                        dp[i].add(a // b)  # 나머지 무시

        if number in dp[i]:
            return i

    return -1