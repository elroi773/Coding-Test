def solution(arr):
    answer = []
    for num in arr:
        if num >= 50 and num % 2 == 0:
            answer.append(num // 2)
        elif num < 50 and num % 2 == 1:
            answer.append(num * 2)
        else:
            answer.append(num)
    return answer