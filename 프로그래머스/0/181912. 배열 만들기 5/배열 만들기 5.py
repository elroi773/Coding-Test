def solution(intStrs, k, s, l):
    answer = []
    for num_str in intStrs:
        # s번 인덱스부터 l 길이만큼 자르고 정수로 변환
        sub_str = num_str[s:s + l]
        sub_num = int(sub_str)
        
        # 정수 값이 k보다 큰지 확인
        if sub_num > k:
            answer.append(sub_num)
    
    return answer
