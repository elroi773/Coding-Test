def solution(number, limit, power):
    answer = 0

    for n in range(1, number + 1):
        count = 0
        i = 1
        while i * i <= n:
            if n % i == 0:
                if i * i == n:
                    count += 1
                else:
                    count += 2
            i += 1

        if count > limit:
            answer += power
        else:
            answer += count

    return answer
