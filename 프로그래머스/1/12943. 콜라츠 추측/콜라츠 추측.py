def solution(num):
    count = 0
    n = num

    while n != 1 and count < 500:
        if n % 2 == 0:
            n //= 2
        else:
            n = n * 3 + 1
        count += 1

    return count if n == 1 else -1
