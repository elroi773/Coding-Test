def solution(my_string, indices):
    remove_set = set(indices)  # 제거할 인덱스를 Set으로 만들어 빠르게 확인
    answer = ''  # 결과 문자열

    for i in range(len(my_string)):
        if i not in remove_set:  # 제거 대상이 아니면
            answer += my_string[i]  # 결과에 추가

    return answer
