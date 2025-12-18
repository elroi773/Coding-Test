def solution(n, a, b):
    round_cnt = 0

    while a != b:
        a = (a + 1) // 2
        b = (b + 1) // 2
        round_cnt += 1

    return round_cnt
