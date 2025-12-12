def solution(people, limit):
    people.sort()  # 몸무게 오름차순 정렬
    left = 0
    right = len(people) - 1
    boats = 0

    while left <= right:
        # 가장 가벼운 + 가장 무거운 사람이 함께 탈 수 있으면 같이 보냄
        if people[left] + people[right] <= limit:
            left += 1
        # 가장 무거운 사람은 항상 보트 하나를 차지
        right -= 1
        boats += 1

    return boats
