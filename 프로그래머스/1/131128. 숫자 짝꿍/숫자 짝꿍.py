from collections import Counter

def solution(X, Y):
    # 각 숫자의 등장 횟수를 센다
    count_x = Counter(X)
    count_y = Counter(Y)
    
    result = []

    # 9부터 0까지 확인하며 공통으로 존재하는 숫자를 조합
    for digit in map(str, range(9, -1, -1)):
        if digit in count_x and digit in count_y:
            min_count = min(count_x[digit], count_y[digit])
            result.append(digit * min_count)
    
    if not result:
        return "-1"
    
    answer = ''.join(result)
    
    # 모두 0으로만 구성된 경우
    if answer[0] == '0':
        return "0"
    
    return answer
