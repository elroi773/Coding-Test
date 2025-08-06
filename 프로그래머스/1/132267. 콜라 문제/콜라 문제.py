def solution(a, b, n):
    answer = 0
    bottles = n

    while bottles >= a:
        exchanged = (bottles // a) * b
        answer += exchanged
        bottles = (bottles % a) + exchanged

    return answer
