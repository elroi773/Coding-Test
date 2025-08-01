def solution(food):
    left = ""

    for i in range(1, len(food)):
        count = food[i] // 2  # 양쪽에 공평하게 나눌 수 있는 개수
        left += str(i) * count

    right = left[::-1]  # 왼쪽을 뒤집은 문자열
    answer = left + "0" + right

    return answer
