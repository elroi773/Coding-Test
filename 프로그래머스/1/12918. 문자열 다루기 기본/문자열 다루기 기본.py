def solution(s):
    # 길이가 4 또는 6인지 확인
    if len(s) not in (4, 6):
        return False
    
    # 모두 숫자인지 확인
    return s.isdigit()
