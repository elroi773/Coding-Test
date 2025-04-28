def solution(myString):
    # "x"를 기준으로 문자열을 나누기
    parts = myString.split('x')
    
    # 나누어진 각 부분의 길이를 리스트로 변환
    answer = [len(part) for part in parts]
    
    return answer
