def solution(my_string):
    # 모든 접미사 생성
    answer = [my_string[i:] for i in range(len(my_string))]
    
    # 사전순 정렬
    answer.sort()
    
    return answer
