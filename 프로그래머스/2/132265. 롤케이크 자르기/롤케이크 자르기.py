def solution(topping):
    from collections import Counter
    
    answer = 0
    
    # 왼쪽에서부터 각 지점까지의 '서로 다른 토핑 수'
    left_count = set()
    left_unique = [0] * len(topping)
    
    for i in range(len(topping)):
        left_count.add(topping[i])
        left_unique[i] = len(left_count)
    
    # 오른쪽에서부터 각 지점까지의 '서로 다른 토핑 수'
    right_count = set()
    right_unique = [0] * len(topping)
    
    for i in range(len(topping) - 1, -1, -1):
        right_count.add(topping[i])
        right_unique[i] = len(right_count)
    
    # i번째 토핑 뒤를 자른다고 생각하면
    # left_unique[i] == right_unique[i+1] 이면 공평
    for i in range(len(topping) - 1):
        if left_unique[i] == right_unique[i + 1]:
            answer += 1
    
    return answer
