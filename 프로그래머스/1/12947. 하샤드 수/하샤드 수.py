def solution(x):
    # 자릿수 합 구하기
    digit_sum = sum(int(d) for d in str(x))
    
    # 하샤드 수 판별
    return x % digit_sum == 0
