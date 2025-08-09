def solution(num_list):
    odd_str = ""
    even_str = ""
    
    for num in num_list:
        if num % 2 == 1:
            odd_str += str(num)
        else:
            even_str += str(num)
    
    # 이어 붙인 문자열을 정수로 변환 후 합산
    answer = int(odd_str) + int(even_str)
    return answer
