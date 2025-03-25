def solution(a, b):
    answer = 0
    ab_str = str(a) + str(b)
    ba_str = str(b) + str(a)
    
    ab = int(ab_str)
    ba = int(ba_str)
    return max(ab, ba)